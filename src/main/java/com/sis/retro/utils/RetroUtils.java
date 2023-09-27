package com.sis.retro.utils;

import com.sis.retro.dto.FeedbackItemDto;
import com.sis.retro.dto.RetroDto;
import com.sis.retro.entity.FeedbackItem;
import com.sis.retro.entity.Retro;
import org.springframework.stereotype.Component;

import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Component
public class RetroUtils {

    DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

    public Retro convertToEntity(RetroDto retroDto){

        LocalDate date = LocalDate.parse(retroDto.getDate(),DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return Retro.builder()
                .name(retroDto.getName())
                .summary(retroDto.getSummary())
                .date(date)
                .participants(retroDto.getParticipants()).build();

    }

    public RetroDto convertToDto(Retro retro){

        return RetroDto.builder()
                .name(retro.getName())
                .summary(retro.getSummary())
                .participants(retro.getParticipants())
                .date(retro.getDate().format(builder.appendPattern("dd/MM/yyyy").toFormatter()))
                .build();
    }

    public FeedbackItem convertToEntity(FeedbackItemDto feedbackItemDto) {

        return FeedbackItem.builder()
                .providerName(feedbackItemDto.getProviderName())
                .body(feedbackItemDto.getBody())
                .type(feedbackItemDto.getType())
                .build();
    }
}
