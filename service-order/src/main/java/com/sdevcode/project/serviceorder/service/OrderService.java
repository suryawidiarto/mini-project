package com.sdevcode.project.serviceorder.service;

import com.sdevcode.project.serviceorder.dto.OrderRequestDTO;
import com.sdevcode.project.serviceorder.repository.projection.QueryBuyerOrderPower;
import com.sdevcode.project.serviceorder.repository.projection.QueryOrderByOrderNumber;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {
    void createOrder(OrderRequestDTO orderRequest);
    void deleteOrder(Long order_id);

    List<Map<String, Object>> getOrderByBuyerId(Long buyer_id);

    List<QueryOrderByOrderNumber> getOrderByOrderNumber(String order_number);

    List<QueryBuyerOrderPower> getBuyerOrderPower();

}
