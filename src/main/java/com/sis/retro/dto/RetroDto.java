package com.sis.retro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Builder
public class RetroDto {

    @NotBlank(message = "Retro name is required")
    private String name;
    private String summary;
    @NotBlank(message = "Retro date is required")
    private String date;
    @NotBlank(message = "Participants is required")
    private String participants;
    private List<FeedbackItemDto> feedbackItems;
}
