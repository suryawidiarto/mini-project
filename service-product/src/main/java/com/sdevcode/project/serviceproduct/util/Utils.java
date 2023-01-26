package com.sdevcode.project.serviceproduct.util;

import com.sdevcode.project.serviceproduct.dto.ProductResponse;
import com.sdevcode.project.serviceproduct.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

@Slf4j
public class Utils {
    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .product_id(product.getProduct_id())
                .product_name(product.getProduct_name())
                .product_description(product.getProduct_description())
                .product_price(product.getProduct_price())
                .build();
    }
}
