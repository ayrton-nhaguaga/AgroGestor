package com.ayrton.AgroGestor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InputApplicationDTO {

    @NotNull
    private String cropId;

    @NotNull
    @Size(min = 0, max = 100)
    private String product;

    @NotNull
    private double dose;

    @NotNull
    private LocalDate applicationDate;

    @NotNull
    @Size(min = 0, max = 50)
    private String employee;

    @NotNull
    @Size(min = 0, max = 500)
    private String notes;
}
