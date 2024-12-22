package com.product.management.product.controller;

import com.product.management.product.dtos.ApiResponse;
import com.product.management.product.dtos.CategoryCreateDTO;
import com.product.management.product.exception.CategoryNotFoundException;  // Import the custom exception
import com.product.management.product.model.Category;
import com.product.management.product.service.interfaces.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceInterface categoryServiceInterface;
    
    // Method: Fetch paginated categories
    // Endpoint: GET /api/categories
    @GetMapping
    public ResponseEntity<?> getCategories(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(categoryServiceInterface.getCategoriesByPages(page, size), HttpStatus.OK);
    }

    // Method: Create a new category
    // Endpoint: POST /api/categories
    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        try {
            ApiResponse apiResponse = categoryServiceInterface.createCategory(categoryCreateDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method: Fetch a category by its ID
    // Endpoint: GET /api/categories/{id}
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryServiceInterface.getCategoryById(id);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    // Method: Update a category by its ID
    // Endpoint: PUT /api/categories/{id}
    @PutMapping("/upadateCategory/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryCreateDTO categoryCreateDTO) {
        try {
            ApiResponse apiResponse = categoryServiceInterface.updateCategory(id, categoryCreateDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    // Method: Delete a category by its ID
    // Endpoint: DELETE /api/categories/{id}
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            ApiResponse apiResponse = categoryServiceInterface.deleteCategory(id);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

   
}
