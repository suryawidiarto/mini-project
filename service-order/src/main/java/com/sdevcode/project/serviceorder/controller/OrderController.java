package com.sdevcode.project.serviceorder.controller;

import com.sdevcode.project.serviceorder.dto.GetOrderRequestDTO;
import com.sdevcode.project.serviceorder.dto.OrderRequestDTO;
import com.sdevcode.project.serviceorder.repository.projection.QueryBuyerOrderPower;
import com.sdevcode.project.serviceorder.repository.projection.QueryOrderByOrderNumber;
import com.sdevcode.project.serviceorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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

    @PostMapping(value = "/get-order-buyer")
    public ResponseEntity<Map<String, Object>> getOrder(@Valid @RequestBody GetOrderRequestDTO getOrderRequestDTO){
        try{
            List<Map<String, Object>> orderByBuyerId = orderService.getOrderByBuyerId(getOrderRequestDTO.getBuyer_id());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Get order successfull");
            response.put("order", orderByBuyerId);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Something when wrong when getting order");
            log.info("Error: {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "/get-order/{order_number}")
    public ResponseEntity<Map<String, Object>> getOrder(@PathVariable("order_number") String order_number){
        try{
            List<QueryOrderByOrderNumber> getOrderByOrderNumber = orderService.getOrderByOrderNumber(order_number);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Get order successfull");
            response.put("order", getOrderByOrderNumber);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Something when wrong when getting order");
            log.info("Error: {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "/get-order/buy-power")
    public ResponseEntity<Map<String, Object>> getOrderBuyPower(){
        try{
            List<QueryBuyerOrderPower> buyerOrderPower = orderService.getBuyerOrderPower();

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Get order successfull");
            response.put("order", buyerOrderPower);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Something when wrong when getting order");
            log.info("Error: {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping(value = "/delete-order/{order_id}")
    public ResponseEntity<Map<String, Object>> postDeleteProduct(@PathVariable("order_id") Long order_id){
        try{
            orderService.deleteOrder(order_id);
            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("message", "Product successfully deleted");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseObject);
        }catch (Exception e){
            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("message", "Something went wrong when deleting the product");

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseObject);
        }
    }
}
