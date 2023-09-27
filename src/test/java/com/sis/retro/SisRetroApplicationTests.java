package com.sis.retro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.retro.dto.RetroDto;
import com.sis.retro.repository.RetroRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SisRetroApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    RetroRepo retroRepo;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void shouldCreateNewRetro()  throws Exception{

        String json = objectMapper.writeValueAsString(getRetroDto());
        mockMvc.perform(MockMvcRequestBuilders.post("/retro")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Assertions.assertEquals(1,retroRepo.findAll().size());

        String expectedResult = "{\"name\":\"Retro1\",\"summary\":\"Retro1 summary\",\"date\":\"01/01/2023\",\"participants\":\"Jack,Harry\",\"feedbackItems\":null}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/retro/Retro1")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    private RetroDto getRetroDto(){

        return RetroDto.builder()
                .name("Retro1")
                .summary("Retro1 summary")
                .date("01/01/2023")
                .participants("Jack,Harry")
                .build();
    }

}
