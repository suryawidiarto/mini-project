package com.sdevcode.project.serviceproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String product_id;
    private String product_name;
    private String product_description;
    private BigDecimal product_price;
}
