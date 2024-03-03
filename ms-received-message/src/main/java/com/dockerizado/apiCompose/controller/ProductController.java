package com.dockerizado.apiCompose.controller;

import com.dockerizado.apiCompose.entity.Product;
import com.dockerizado.apiCompose.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    public ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product productCreated = productRepository.save(product);
        return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
    }
}
