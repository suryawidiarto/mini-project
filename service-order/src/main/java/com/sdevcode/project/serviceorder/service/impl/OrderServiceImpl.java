package com.sdevcode.project.serviceorder.service.impl;

import com.sdevcode.project.serviceorder.dto.OrderRequestDTO;
import com.sdevcode.project.serviceorder.model.Order;
import com.sdevcode.project.serviceorder.model.OrderItems;
import com.sdevcode.project.serviceorder.repository.OrderRepository;
import com.sdevcode.project.serviceorder.repository.projection.QueryBuyerOrderPower;
import com.sdevcode.project.serviceorder.repository.projection.QueryOrderByBuyerId;
import com.sdevcode.project.serviceorder.repository.projection.QueryOrderByOrderNumber;
import com.sdevcode.project.serviceorder.service.OrderService;
import com.sdevcode.project.serviceorder.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(OrderRequestDTO orderRequest) {
        try{
            List<OrderItems> listOrderItems = orderRequest.getOrder_items()
                    .stream()
                    .map(Utils::mapToListOrderItems).toList();

            BigDecimal price = BigDecimal.valueOf(0);

            for(var orderItems : listOrderItems){
                price = price.add(orderItems.getOrder_product_price().multiply(BigDecimal.valueOf(orderItems.getOrder_quantity())));
            }

            Order order = Order.builder()
                    .order_number(UUID.randomUUID().toString())
                    .buyer_id(orderRequest.getBuyer_id())
                    .payment_method(orderRequest.getPayment_method())
                    .order_items(listOrderItems)
                    .total_price(price)
                    .timestamp(System.currentTimeMillis())
                    .build();

            Order savedOrder = orderRepository.save(order);
            log.info("Order Created Successfully, order -> {}", savedOrder);
        }catch (Exception e){
            throw new RuntimeException("Create Order Error -> "+ e.getMessage());
        }
    }

    @Override
    public void deleteOrder(Long order_id) {
        try{
            orderRepository.deleteById(order_id);
            Optional<Order> checkResult = orderRepository.findById(order_id);
            if(checkResult.isEmpty()){
                log.info("Delete Order With Id - {} - Success", order_id);
            }else{
                log.info("Delete Order With Id - {} - Failed", order_id);
                throw new RuntimeException("Failed delete product");
            }
        }catch (Exception e){
            throw new RuntimeException("Error deleting Order -> "+ e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>>getOrderByBuyerId(Long buyer_id) {
        List<QueryOrderByBuyerId> orderByBuyerId = orderRepository.getOrderByBuyerId(buyer_id);

        List<Map<String, Object>> mappedOrder = orderByBuyerId.stream().map(each -> {
            Map<String, Object> eachOrder = new HashMap<>();
            eachOrder.put("order_id", each.getOrderId());
            eachOrder.put("buyer_id", each.getBuyerId());
            eachOrder.put("order_number", each.getOrderNumber());
            eachOrder.put("payment_method", each.getPaymentMethod());
            eachOrder.put("timestamp", each.getTimestamp());
            eachOrder.put("total_order_price", each.getTotalOrderPrice());

            record ProductDetails(
                    Long order_items_id,
                    String order_product_id,
                    String order_product_name,
                    String order_product_description,
                    BigDecimal order_product_price,
                    Integer order_quantity,
                    BigDecimal totalProductPrice,
                    Long order_id
            ) { }
            eachOrder.put("product-details", new ProductDetails(each.getOrderItemsId(), each.getOrderProductId(), each.getOrderProductName(), each.getOrderProductDescription(), each.getOrderProductPrice(), each.getOrderQuantity(), each.getTotalProductPrice(), each.getItemsOrderId()));
            return eachOrder;
        }).toList();

        log.info("getOrderByBuyerId -> {}",mappedOrder);
        return mappedOrder;

    }

    @Override
    public List<QueryOrderByOrderNumber> getOrderByOrderNumber(String order_number) {
        return orderRepository.getorderByOrderNumber(order_number);
    }

    @Override
    public List<QueryBuyerOrderPower> getBuyerOrderPower() {
        return orderRepository.getBuyerOrderPower();
    }
}
