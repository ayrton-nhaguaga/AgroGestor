package com.ayrton.AgroGestor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "promotions")
@Data
public class Promotion {

    @Id
    private String id;
    private String title;
    private String description;
    private double discountPercent;
    private String code;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private boolean active;
}
