package com.sdevcode.project.serviceorder.repository.projection;

import java.math.BigDecimal;

public interface QueryOrderByOrderNumber {
    Long getOrderId();
    String getOrderNumber();
    Long getBuyerId();
    String getPaymentMethod();
    BigDecimal getTotalPrice();
    Long getTimestamp();
}
