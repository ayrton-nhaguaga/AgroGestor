package com.ayrton.AgroGestor.dto;

import com.ayrton.AgroGestor.enums.ProductType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {

    @NotNull
    @Size(min = 0, max = 50)
    private String name;

    @NotNull
    private ProductType productType;

    @NotNull
    @Size(min = 0, max = 500)
    private String description;

    @NotNull
    private String unit;
}
