package com.ayrton.AgroGestor.model;

import com.ayrton.AgroGestor.enums.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    @Id
    private String id;

    private String cropId;
    private String buyerName;
    private double amount;
    private double pricePerUnit;
    private double totalPrice;

    private LocalDate date;

    private SaleStatus status;
}

