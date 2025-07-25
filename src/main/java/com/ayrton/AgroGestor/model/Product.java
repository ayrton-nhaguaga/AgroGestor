package com.ayrton.AgroGestor.model;

import com.ayrton.AgroGestor.enums.ProductType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private ProductType productType;
    private String description;
    private String unit; // "kg", "litro", "saco", etc

}
