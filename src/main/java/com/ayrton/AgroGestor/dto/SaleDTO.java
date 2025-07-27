package com.ayrton.AgroGestor.dto;

import com.ayrton.AgroGestor.enums.SaleStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaleDTO {

    @NotNull
    private String cropId;

    @NotNull
    @Size(min = 0, max = 50)
    private String buyerName;

    @NotNull
    private double amount;

    @NotNull
    private double pricePerUnit;

    @NotNull
    private double totalPrice;

    @NotNull
    private String promotionCode;

    @NotNull
    LocalDateTime saleDate;

    @NotNull
    private SaleStatus status;
}
