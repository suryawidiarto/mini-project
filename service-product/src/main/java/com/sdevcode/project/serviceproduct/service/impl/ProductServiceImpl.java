package com.sdevcode.project.serviceproduct.service.impl;

import com.sdevcode.project.serviceproduct.dto.ProductRequest;
import com.sdevcode.project.serviceproduct.dto.ProductResponse;
import com.sdevcode.project.serviceproduct.model.Product;
import com.sdevcode.project.serviceproduct.repository.ProductRepository;
import com.sdevcode.project.serviceproduct.service.ProductService;
import com.sdevcode.project.serviceproduct.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void addProduct(ProductRequest productRequest) {
        try{
            Product newProduct =  Product.builder()
                    .product_name(productRequest.getProduct_name())
                    .product_description(productRequest.getProduct_description())
                    .product_price(productRequest.getProduct_price())
                    .build();
            productRepository.save(newProduct);
            log.info("Saving Product Success - {}", newProduct.getProduct_id());
        }catch (Exception e){
            log.info("Saving Product Error | err -> {}", e.getMessage());
            throw new RuntimeException("Error adding product -> "+ e.getMessage());
        }
    }

    @Override
    public void deleteProductById(String id) {
        try{
            productRepository.deleteById(id);
            Optional<Product> checkResult = productRepository.findById(id);
            if(checkResult.isEmpty()){
                log.info("Delete Product With Id - {} - Success", id);
            }else{
                log.info("Delete Product With Id - {} - Failed", id);
                throw new RuntimeException("Failed delete product");
            }
        }catch (Exception e){
            log.info("Delete Product With Id - {} - Error | err -> {}", id, e.getMessage());
            throw new RuntimeException("Error deleting product -> "+ e.getMessage());
        }

    }

    @Override
    public List<ProductResponse> allProduct(Integer page, Integer size) {
        try{
            Pageable pageRequest = PageRequest.of(page - 1, size, Sort.by("product_name").ascending());
            List<Product> findResult = productRepository.findAll(pageRequest).stream().toList();
            log.info("Get list of product success");
            return findResult.stream().map(Utils::mapToProductResponse).collect(Collectors.toList());
        }catch (Exception e){
            log.info("Get list of product error | err -> {}", e.getMessage());
            throw new RuntimeException("Error getting product -> "+ e.getMessage());
        }
    }

    @Override
    public void updateProduct(String id, ProductRequest productRequest) {
        try{
            Optional<Product> findProduct = productRepository.findById(id);
            if(findProduct.isPresent()){
                Product product = findProduct.get();
                product.setProduct_name(productRequest.getProduct_name() == null ? product.getProduct_name() : productRequest.getProduct_name());
                product.setProduct_description(productRequest.getProduct_description() == null ? product.getProduct_description() : productRequest.getProduct_description());
                product.setProduct_price(productRequest.getProduct_price() == null ? product.getProduct_price() : productRequest.getProduct_price());
                productRepository.save(product);
                log.info("product with id {}, successfully updated", id);
            }else {
                log.info("product with id {}, not found", id);
                throw new RuntimeException("product with id "+id+" not found");
            }
        }catch (Exception e){
            log.info("Error updating product data to mongodb -> {}", e.getMessage());
            throw new RuntimeException("Error updating product -> "+ e.getMessage());
        }
    }
}

