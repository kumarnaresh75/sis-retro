package com.sis.retro.utils;

import com.sis.retro.dto.RetroDto;
import com.sis.retro.entity.Retro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RetroUtilsTest {

    RetroUtils retroUtils = new RetroUtils();

    @Test
    public void convertToEntity(){
        RetroDto retroDto = RetroDto.builder()
                .name("test")
                .date("01/01/2023")
                .summary("test")
                .participants("jack,harry")
                .build();

        Retro retro = retroUtils.convertToEntity(retroDto);
        LocalDate date = LocalDate.parse(retroDto.getDate(),DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        assertEquals(date,retro.getDate());
        assertEquals(retroDto.getName(),retro.getName());
    }

    @Test
    public void convertToDto(){

        Retro retro = Retro.builder()
                .name("test").summary("test").participants("Jack,Harry")
                .date(LocalDate.parse("01/01/2023",DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();

        RetroDto retroDto = retroUtils.convertToDto(retro);

        assertEquals("01/01/2023",retroDto.getDate());

    }

}