package com.ayrton.AgroGestor.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockDTO {

    @NotNull
    private String farmId;

    @NotNull
    private String productId;

    @NotNull
    private double quantity;

    @NotNull
    private LocalDateTime lastRestockDate;
}
