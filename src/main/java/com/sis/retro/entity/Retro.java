package com.sis.retro.entity;

import com.sis.retro.dto.FeedbackItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Builder
public class Retro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String name;
    private String summary;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String participants;
    @OneToMany(cascade= CascadeType.ALL)
    private List<FeedbackItem> feedbackItems;
}
