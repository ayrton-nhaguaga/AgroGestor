package com.ayrton.AgroGestor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CropDTO {

    @NotNull
    private String fieldId;

    @NotNull
    @Size(min = 0, max = 50)
    private String culture;

    @NotNull
    private LocalDate plantedDate;

    @NotNull
    private LocalDate harvestDate;

    @NotNull
    private double expectedYield;

    @NotNull
    private double costPerHectare;
}
