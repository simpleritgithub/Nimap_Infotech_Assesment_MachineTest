package com.product.management.product.controller;

import com.product.management.product.dtos.ApiResponse;
import com.product.management.product.dtos.ProductCreateDTO;
import com.product.management.product.exception.ProductNotFoundException;
import com.product.management.product.service.interfaces.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceInterface productServiceInterface;

    // Method: Fetch a product by its ID.
    // Endpoint: GET /api/products/getById/{id}
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(productServiceInterface.getProductById(id));
        } catch (ProductNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage()));
        }
    }

    // Method: Create a new product.
    // Endpoint: POST /api/products
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(productServiceInterface.createProduct(productCreateDTO));
        } catch (ProductNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage()));
        }
    }

    // Method: Update a product by its ID.
    // Endpoint: PUT /api/products/updateProduct/{id}
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDTO productCreateDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(productServiceInterface.updateProduct(id, productCreateDTO));
        } catch (ProductNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage()));
        }
    }

    // Method: Delete a product by its ID.
    // Endpoint: DELETE /api/products/deleteById/{id}
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(productServiceInterface.deleteProduct(id));
        } catch (ProductNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage()));
        }
    }
}
