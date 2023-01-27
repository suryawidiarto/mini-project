package com.sdevcode.project.serviceorder.repository.projection;

import java.math.BigDecimal;

public interface QueryBuyerOrderPower {
    Long getBuyerId();
    Integer getTotalOrder();
    Integer getTotalItemBuy();
    BigDecimal getTotalBuyValue();
}
