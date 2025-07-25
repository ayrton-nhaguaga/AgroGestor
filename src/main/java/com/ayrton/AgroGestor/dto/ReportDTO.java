package com.ayrton.AgroGestor.dto;

import com.ayrton.AgroGestor.enums.ReportType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Map;

public class ReportDTO {
    @NotNull
    private String id;

    @NotNull
    private ReportType type;

    @NotNull
    private LocalDateTime generatedAt;

    @NotNull
    private Map<String, Object> data;
}
