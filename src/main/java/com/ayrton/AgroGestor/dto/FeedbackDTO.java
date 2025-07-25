package com.ayrton.AgroGestor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackDTO {
    @NotNull
    private String userId;

    @NotNull
    private String bookingId;

    @NotNull
    private int rating; // de 1 a 5

    @NotNull
    @Size(min = 0, max = 500)
    private String comment;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();
}
