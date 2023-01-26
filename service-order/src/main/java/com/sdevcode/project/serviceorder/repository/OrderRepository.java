package com.sdevcode.project.serviceorder.repository;

import com.sdevcode.project.serviceorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // get specific order with id
    @Query(nativeQuery = true, value = "SELECT * FROM order_table WHERE order_id = :order_id")
    List<Order> getOrderByOrderId(@Param("order_id") Long order_id);

    // get all order with specific buyer
    @Query(nativeQuery = true, value = "SELECT t1.order_id AS orderId, t1.buyer_id AS buyerId, t1.order_number AS orderNumber, t1.payment_method AS paymentMethod, t1.timestamp AS timestamp, t1.total_price AS totalPrice, t2.order_items_id AS orderItemsId, t2.order_product_description AS orderProductDescription, t2.order_product_id AS orderProductId, t2.order_product_name AS orderProductName, t2.order_product_price AS orderProductPrice, t2.order_quantity AS orderQuantity, t2.order_id AS itemsOrderId FROM order_table AS t1 INNER JOIN order_items_table AS t2 ON t1.order_id = t2.order_id WHERE t1.buyer_id = :buyerId AND t2.order_quantity > 5 ORDER BY t2.order_product_price DESC")
    List<Object> getOrderByBuyerId(@Param("buyer_id") Long buyerId);


    // get all order with specific product
    // get all order with specific date ?
    // get all order with specific range total
    // get all order with specific payment_method
}