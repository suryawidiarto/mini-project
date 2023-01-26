package com.sdevcode.project.serviceproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotEmpty
    private String product_name;
    @NotEmpty
    private String product_description;
    @NotNull
    private BigDecimal product_price;
}
