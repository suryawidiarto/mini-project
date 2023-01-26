package com.sdevcode.project.serviceorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDTO {

    @NotEmpty
    private String order_product_id;

    @NotEmpty
    private String order_product_name;

    @NotEmpty
    private String order_product_description;

    @NotNull
    private BigDecimal order_product_price;

    @NotNull
    private Integer order_quantity;
}
