package com.sis.retro.repository;

import com.sis.retro.entity.Retro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

@DataJpaTest
public class RetroRepoTest {
    @Autowired
    private RetroRepo retroRepo;
    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    public void testPersistRetro(){

        Retro retro = Retro.builder()
                        .name("Retro1").summary("Retro1 summary").participants("Jack,Harry")
                        .date(LocalDate.now()).build();

        testEntityManager.persist(retro);

        Retro dbRetro = retroRepo.findByName("Retro1");
        Assertions.assertEquals(1,dbRetro.getId());
    }
}
