package com.sdevcode.project.serviceorder.controller;

import com.sdevcode.project.serviceorder.dto.OrderRequestDTO;
import com.sdevcode.project.serviceorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/create-order")
    public ResponseEntity<Map<String, Object>> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO){
        try{
            orderService.createOrder(orderRequestDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order created successfully");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Something when wrong when creating order");
            log.info("Error: {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
