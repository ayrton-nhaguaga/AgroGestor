package com.ayrton.AgroGestor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "input_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputApplication {
    @Id
    private String id;

    private String cropId;
    private String product;
    private double dose;

    private LocalDate applicationDate;
    private String employee;
    private String notes;
}

