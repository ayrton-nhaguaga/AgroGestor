package com.ayrton.AgroGestor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PromotionDTO {

    @NotNull
    @Size(min = 0, max = 50)
    private String title;

    @Size(min = 0, max = 350)
    private String description;

    @NotNull
    private double discountPercent;

    @NotNull
    private String code;

    @NotNull
    private LocalDateTime validFrom;

    @NotNull
    private LocalDateTime validTo;

    @NotNull
    private boolean active;
}
