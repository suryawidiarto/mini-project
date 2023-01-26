package com.sdevcode.project.serviceproduct.controller;

import com.sdevcode.project.serviceproduct.dto.ProductRequest;
import com.sdevcode.project.serviceproduct.dto.ProductResponse;
import com.sdevcode.project.serviceproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<Map<String, Object>> getAllProduct(@RequestParam("page") @NotNull @Min(1) Integer page, @RequestParam("size") @NotNull Integer size){
        try{
            List<ProductResponse> products = productService.allProduct(page, size);
            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("product", products);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseObject);
        }catch (Exception e){
            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("message", "Something went wrong when getting the product");

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseObject);
        }

    }

    @PostMapping(value = "/add-product")
    public ResponseEntity<Map<String, Object>> postAddProduct(@Valid @RequestBody ProductRequest productRequest){
        try{
            productService.addProduct(productRequest);
            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("message", "Product successfully created");

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(responseObject);
        }catch (Exception e){
            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("message", "Something went wrong when creating the product");

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseObject);
        }
    }

    @PostMapping(value = "/delete-product/{id-product}")
    public ResponseEntity<Map<String, Object>> postDeleteProduct(@PathVariable("id-product") String id){
        try{
            productService.deleteProductById(id);
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

    @PostMapping(value = "/update-product/{id-product}")
    public ResponseEntity<Map<String, Object>> postUpdateProduct(@PathVariable("id-product") String id, @RequestBody ProductRequest productRequest){
        try{
            productService.updateProduct(id, productRequest);
            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("message", "Product successfully updated");

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

