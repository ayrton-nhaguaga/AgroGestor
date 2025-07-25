package com.ayrton.AgroGestor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Field {
    @Id
    private String id;

    private String name;
    private String farmId;
    private double area;

    private List<Double> coordinates; // latitude, longitude
}
