package com.sdevcode.project.serviceproduct.service;

import com.sdevcode.project.serviceproduct.dto.ProductRequest;
import com.sdevcode.project.serviceproduct.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void addProduct(ProductRequest productRequest);
    void deleteProductById(String id);
    List<ProductResponse> allProduct(Integer page, Integer size);
    void updateProduct(String id,ProductRequest productRequest);
}
