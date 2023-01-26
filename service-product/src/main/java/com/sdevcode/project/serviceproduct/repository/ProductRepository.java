package com.sdevcode.project.serviceproduct.repository;

import com.sdevcode.project.serviceproduct.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
