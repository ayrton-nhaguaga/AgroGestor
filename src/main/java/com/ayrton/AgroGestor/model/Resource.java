package com.ayrton.AgroGestor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "resources")
@Data
public class Resource {

    @Id
    private String id;
    private String name;
    private String type;
    private boolean active;
}
