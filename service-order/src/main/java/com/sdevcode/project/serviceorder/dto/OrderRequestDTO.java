package com.sdevcode.project.serviceorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    @NotNull
    private Long buyer_id;

    @NotEmpty
    private String payment_method;

    @Valid
    @NotNull
    private List<OrderItemsDTO> order_items;
}
