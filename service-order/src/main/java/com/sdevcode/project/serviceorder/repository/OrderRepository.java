package com.sdevcode.project.serviceorder.repository;

import com.sdevcode.project.serviceorder.model.Order;
import com.sdevcode.project.serviceorder.repository.projection.QueryBuyerOrderPower;
import com.sdevcode.project.serviceorder.repository.projection.QueryOrderByBuyerId;
import com.sdevcode.project.serviceorder.repository.projection.QueryOrderByOrderNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // get specific order with order_number
    @Query(nativeQuery = true, value = "SELECT order_id AS orderId, buyer_id AS buyerId, order_number AS orderNumber, payment_method AS paymentMethod, timestamp, total_price AS totalPrice  FROM order_table WHERE order_number = :order_number")
    List<QueryOrderByOrderNumber> getorderByOrderNumber(@Param("order_number") String order_number);

    // get all order data with specific buyer
    @Query(nativeQuery = true, value = "SELECT " +
            "t1.order_id AS orderId, t1.buyer_id AS buyerId, t1.order_number AS orderNumber, t1.payment_method AS paymentMethod, t1.timestamp AS timestamp, t1.total_price AS totalOrderPrice, " +
            "t2.order_items_id AS orderItemsId, t2.order_product_description AS orderProductDescription, t2.order_product_id AS orderProductId, t2.order_product_name AS orderProductName, t2.order_product_price AS orderProductPrice, t2.order_quantity AS orderQuantity, t2.order_quantity * t2.order_product_price AS totalProductPrice, t2.order_id AS itemsOrderId " +
            "FROM order_table AS t1 INNER JOIN order_items_table AS t2 ON t1.order_id = t2.order_id " +
            "WHERE t1.buyer_id = :buyer_id ORDER BY t1.timestamp DESC")
    List<QueryOrderByBuyerId> getOrderByBuyerId(@Param("buyer_id") Long buyer_id);

    //get total_order and total_buy_value with pov of buyer_id
    @Query(nativeQuery = true, value = "SELECT " +
            "buyer_id AS buyerId, COUNT(order_number) AS totalOrder, SUM(total_item_buy) AS totalItemBuy, SUM(total_price) AS totalBuyValue " +
            "FROM (SELECT t1.order_id, t1.buyer_id, t1.order_number, t1.total_price, COUNT(t2.order_items_id) AS total_item_buy FROM order_table AS t1 INNER JOIN order_items_table AS t2 ON t1.order_id = t2.order_id GROUP BY t1.order_number, t1.order_id) AS filtered_table " +
            "GROUP BY buyer_id")
    List<QueryBuyerOrderPower> getBuyerOrderPower();
}