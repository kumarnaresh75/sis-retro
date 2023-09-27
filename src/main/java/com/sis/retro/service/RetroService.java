package com.sis.retro.service;

import com.sis.retro.dto.FeedbackItemDto;
import com.sis.retro.dto.RetroDto;
import com.sis.retro.entity.FeedbackItem;
import com.sis.retro.entity.Retro;
import com.sis.retro.exception.FeedbackNotFoundException;
import com.sis.retro.exception.RetroNotFoundException;
import com.sis.retro.repository.RetroRepo;
import com.sis.retro.utils.RetroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RetroService {

    @Autowired
    private RetroRepo retroRepo;
    @Autowired
    private RetroUtils retroUtils;
    public void saveNewRetro(RetroDto retroDto) {
        retroRepo.save(retroUtils.convertToEntity(retroDto));

    }

    public void saveNewRetroList(List<RetroDto> retroDtoList) {
        List<Retro> entityList = retroDtoList.stream().map(dto -> retroUtils.convertToEntity(dto)).collect(Collectors.toList());
        retroRepo.saveAll(entityList);
        //retroDtoList.stream().forEach(dto -> retroRepo.save(retroUtils.convertToEntity(dto)));
    }

    public RetroDto getRetroByName(String name) throws RetroNotFoundException {
        Retro retro =  retroRepo.findByName(name);
        if(retro!=null)
          return retroUtils.convertToDto(retro);
        else{
            throw new RetroNotFoundException();
        }
    }

    public void saveNewFeedbackItem(String retroName, FeedbackItemDto feedbackItemDto) throws RetroNotFoundException {

        Retro retro = retroRepo.findByName(retroName);
        if(retro==null){
            throw new RetroNotFoundException();
        }
        List<FeedbackItem> feedbackItemList = retro.getFeedbackItems();
        if(retro.getFeedbackItems()==null){
            feedbackItemList = new ArrayList<>();
        }
        feedbackItemList.add(retroUtils.convertToEntity(feedbackItemDto));

        retroRepo.save(retro);
    }

    public void updateFeedbackItem(String retroName, FeedbackItemDto feedbackItemDto) throws RetroNotFoundException, FeedbackNotFoundException {

        Retro retro = retroRepo.findByName(retroName);
        if(retro==null){
            throw new RetroNotFoundException();
        }
        List<FeedbackItem> feedbackItemList = retro.getFeedbackItems();
        if(retro.getFeedbackItems()==null){
            throw new FeedbackNotFoundException();
        }

        Optional<FeedbackItem> feedbackItem = feedbackItemList.stream().filter(fb -> fb.getProviderName().equals(feedbackItemDto.getProviderName())).findFirst();

        if(feedbackItem.isEmpty()){
            throw new FeedbackNotFoundException();
        }

        feedbackItem.get().setBody(feedbackItemDto.getBody());
        feedbackItem.get().setType(feedbackItemDto.getType());
        retroRepo.save(retro);
    }

    public Page<Retro> getAllRetrosByPage(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return retroRepo.findAll(pageable);
    }

    public List<Retro> getRetroByDate(String date) {
        return retroRepo.findByDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    }
}
