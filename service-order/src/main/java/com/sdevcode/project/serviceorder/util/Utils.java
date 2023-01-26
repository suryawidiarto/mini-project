package com.sdevcode.project.serviceorder.util;

import com.sdevcode.project.serviceorder.dto.OrderItemsDTO;
import com.sdevcode.project.serviceorder.model.OrderItems;

public class Utils {
    public static OrderItems mapToListOrderItems(OrderItemsDTO orderItemsDTO){
        return OrderItems.builder()
                .order_product_id(orderItemsDTO.getOrder_product_id())
                .order_product_name(orderItemsDTO.getOrder_product_name())
                .order_product_description(orderItemsDTO.getOrder_product_description())
                .order_product_price(orderItemsDTO.getOrder_product_price())
                .order_quantity(orderItemsDTO.getOrder_quantity())
                .build();
    }
}
