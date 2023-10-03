package com.killian.SpringBoot.ExamApp.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.killian.SpringBoot.ExamApp.models.Product;
import com.killian.SpringBoot.ExamApp.models.ResponseObject;
import com.killian.SpringBoot.ExamApp.repositories.ProductRepository;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {

    // Dependency Injection
    @Autowired
    private ProductRepository repository;

    // Get all products
    @GetMapping("/getAllProducts")
    // Request: http://localhost:8080/api/v1/Products/getAllProducts
    List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get product by id
    @GetMapping("/getProduct/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("Ok", "Query product successfully", foundProduct));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Fail", "Can not find product with id = " + id, null));
        }
    }

    // Delete product by id
    @DeleteMapping("/deleteProduct/{id}")
    ResponseEntity<ResponseObject> deleteById(@PathVariable Long id) {
        boolean productExist = repository.existsById(id);
        if (productExist) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("Ok", "Delete product successfully", repository.findAll()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Fail", "Can not find product with id = " + id, null));
        }
    }

    // Insert new product
    @PostMapping("/insertProduct")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        if (foundProducts.size() > 0)
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject("Fail", "Product already inserted", null));
        else {
            repository.save(newProduct);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("Ok", "Insert product successfully", newProduct));
        }
    }

    // Update product by id
    @PutMapping("/updateProduct/{id}")
    ResponseEntity<ResponseObject> updateById(@PathVariable Long id, @RequestBody Product newProduct) {
        Product updatedProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setPrice(newProduct.getPrice());
                    product.setProductYear(newProduct.getProductYear());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product); // Return the updated product
                })
                .orElseGet(() -> {
                    return repository.save(newProduct);
                });

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Ok", "Product updated", updatedProduct));
    }
}
