package com.gokul.productservice.repository;

import com.gokul.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
