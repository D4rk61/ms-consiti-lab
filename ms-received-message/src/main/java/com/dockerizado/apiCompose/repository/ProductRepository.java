package com.dockerizado.apiCompose.repository;

import com.dockerizado.apiCompose.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Integer> {
}
