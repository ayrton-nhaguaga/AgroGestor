package com.ayrton.AgroGestor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class FieldDTO {

    @NotNull
    @Size(min = 0, max = 50)
    private String name;

    @NotNull
    private String farmId;

    @NotNull
    private double area;

    @NotNull
    private List<Double> coordinates; // latitude, longitude
}
