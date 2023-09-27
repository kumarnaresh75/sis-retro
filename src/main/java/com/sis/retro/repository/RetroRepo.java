package com.sis.retro.repository;

import com.sis.retro.entity.Retro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RetroRepo extends JpaRepository<Retro,Long> {

    public Retro findByName(String name);
    public List<Retro> findByDate(LocalDate date);
}
