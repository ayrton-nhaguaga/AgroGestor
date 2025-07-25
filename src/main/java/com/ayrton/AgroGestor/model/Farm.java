package com.ayrton.AgroGestor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "farms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Farm {
    @Id
    private String id;

    private String name;
    private String location;
    private double sizeHectares;

    private String ownerId;
    private LocalDateTime createdAt = LocalDateTime.now();
}

