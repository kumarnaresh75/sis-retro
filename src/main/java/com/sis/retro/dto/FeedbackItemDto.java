package com.sis.retro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackItemDto {
    private String providerName;
    private String body;
    private String type;
}
