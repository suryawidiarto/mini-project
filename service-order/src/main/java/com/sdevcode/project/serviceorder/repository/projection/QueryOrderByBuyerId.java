package com.sdevcode.project.serviceorder.repository.projection;

import java.math.BigDecimal;

public interface QueryOrderByBuyerId {
    Long getOrderId();
    Long getBuyerId();
    String getOrderNumber();
    String getPaymentMethod();
    Long getTimestamp();
    BigDecimal getTotalOrderPrice();

    Long getOrderItemsId();
    String getOrderProductDescription();
    String getOrderProductId();
    String getOrderProductName();
    BigDecimal getOrderProductPrice();
    Integer getOrderQuantity();
    BigDecimal getTotalProductPrice();
    Long getItemsOrderId();
}

