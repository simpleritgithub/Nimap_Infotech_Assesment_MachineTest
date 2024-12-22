package com.product.management.product.service.interfaces;

import org.hibernate.query.Page;

import com.product.management.product.dtos.ApiResponse;
import com.product.management.product.dtos.CategoryCreateDTO;
import com.product.management.product.model.Category;

public interface CategoryServiceInterface {
	
    ApiResponse createCategory(CategoryCreateDTO categoryCreateDTO);

    Category getCategoryById(Long id);

    ApiResponse updateCategory(Long id, CategoryCreateDTO categoryCreateDTO);;

    ApiResponse deleteCategory(Long id);

    org.springframework.data.domain.Page<Category> getCategoriesByPages(int page, int size);
}