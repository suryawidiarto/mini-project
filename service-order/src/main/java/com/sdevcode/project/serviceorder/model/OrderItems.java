package com.sdevcode.project.serviceorder.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items_table")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_items_id;
    private String order_product_id;
    private String order_product_name;
    private String order_product_description;
    private BigDecimal order_product_price;
    private Integer order_quantity;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;
}
