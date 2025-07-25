package com.ayrton.AgroGestor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "crops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crop {
    @Id
    private String id;

    private String fieldId;
    private String culture;

    private LocalDate plantedDate;
    private LocalDate harvestDate;
    private double expectedYield;
    private double costPerHectare;
}

