package com.ayrton.AgroGestor.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeSchedule {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
