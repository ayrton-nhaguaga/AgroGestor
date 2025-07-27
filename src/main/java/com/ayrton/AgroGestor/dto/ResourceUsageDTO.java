package com.ayrton.AgroGestor.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResourceUsageDTO {

    @NotNull
    private String id;

    @NotNull
    private String resourceId;


    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;
}
