package com.ayrton.AgroGestor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "services")
@Data
public class Catalog {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;

}
