package com.ayrton.AgroGestor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FarmDTO {

    @NotNull
    @Size(min = 0, max = 50)
    private String name;

    @NotNull
    @Size(min = 0, max = 250)
    private String location;

    @NotNull
    private double sizeHectares;

    @NotNull
    private String ownerId;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();
}
