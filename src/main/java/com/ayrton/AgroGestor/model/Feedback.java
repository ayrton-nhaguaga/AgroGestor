package com.ayrton.AgroGestor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "feedbacks")
@Data
public class Feedback {

    @Id
    private String id;
    private String userId;
    private String bookingId;
    private int rating; // de 1 a 5
    private String comment;
    private LocalDateTime createdAt = LocalDateTime.now();
}
