package com.sdevcode.project.serviceorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderRequestDTO {
    @NotNull
    private Long buyer_id;
}
