package com.sis.retro.controller;

import com.sis.retro.dto.FeedbackItemDto;
import com.sis.retro.dto.RetroDto;
import com.sis.retro.entity.Retro;
import com.sis.retro.exception.FeedbackNotFoundException;
import com.sis.retro.exception.RetroNotFoundException;
import com.sis.retro.service.RetroService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/retro")
@RestController
@Slf4j
public class RetroController {

    @Autowired
    private RetroService retroService;
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewRetro(@Valid @RequestBody RetroDto retroDto){
        log.info("New Retro creation request");
        retroService.saveNewRetro(retroDto);
        log.info("Retro Creation successful");
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewRetro(@Valid @RequestBody List<RetroDto> retroDtoList){
        log.info("Bulk creation of Retro requested. Count: "+retroDtoList.size());
        retroService.saveNewRetroList(retroDtoList);
        log.info("Bulk retro creation successful.");
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public RetroDto getRetroByName(@PathVariable String name) throws RetroNotFoundException {
        log.info("Find Retro by name: "+name);
        return retroService.getRetroByName(name);
    }

    @GetMapping(value = "/{pageNumber}/{pageSize}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Page<Retro> getAllRetros(@PathVariable Integer pageNumber, @PathVariable Integer pageSize){
        log.info("Find all Retros: pageNumber: "+pageNumber+" pageSize: "+pageSize);
        return retroService.getAllRetrosByPage(pageNumber,pageSize);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Retro> getRetrosByDate(@RequestParam String date){
        log.info("Find Retros by date: "+date);
        return retroService.getRetroByDate(date);
    }

    @PostMapping("/{retroName}/feedback")
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewFeedback(@PathVariable String retroName,@RequestBody FeedbackItemDto feedbackItemDto) throws RetroNotFoundException {
        log.info("New Feedback request");
        retroService.saveNewFeedbackItem(retroName,feedbackItemDto);

    }

    @PutMapping("/{retroName}/feedback")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void amendFeedback(@PathVariable String retroName,@RequestBody FeedbackItemDto feedbackItemDto) throws RetroNotFoundException, FeedbackNotFoundException {
        log.info("Amend Feedback request");
        retroService.updateFeedbackItem(retroName,feedbackItemDto);

    }
}
