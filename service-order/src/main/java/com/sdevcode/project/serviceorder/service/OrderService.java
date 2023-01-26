package com.sdevcode.project.serviceorder.service;

import com.sdevcode.project.serviceorder.dto.OrderRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void createOrder(OrderRequestDTO orderRequest);
    void deleteOrder(Long order_id);

}
