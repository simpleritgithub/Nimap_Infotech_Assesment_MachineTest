package com.product.management.product.service.implementations;

import com.product.management.product.dtos.ApiResponse;
import com.product.management.product.dtos.CategoryCreateDTO;
import com.product.management.product.model.Category;
import com.product.management.product.repository.CategoryRepository;
import com.product.management.product.exception.CategoryNotFoundException;  // Import the custom exception
import com.product.management.product.service.interfaces.CategoryServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryInterfaceImpl implements CategoryServiceInterface {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse createCategory(CategoryCreateDTO categoryCreateDTO) {
        ApiResponse apiResponse = new ApiResponse("false");

        boolean exists = categoryRepository.existsByNameIgnoreCase(categoryCreateDTO.getName());

        if (!exists) {
            Category category = modelMapper.map(categoryCreateDTO, Category.class);
            categoryRepository.save(category);
            apiResponse = new ApiResponse("true");
        }
        return apiResponse;
    }

    @Override
    public Category getCategoryById(Long id) {
        // Use custom exception instead of RuntimeException
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }

    @Override
    public ApiResponse updateCategory(Long id, CategoryCreateDTO categoryCreateDTO) {
        ApiResponse apiResponse = new ApiResponse("false");

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        category.setName(categoryCreateDTO.getName());
        category.setDescription(categoryCreateDTO.getDescription());
        categoryRepository.save(category);
        apiResponse = new ApiResponse("true");

        return apiResponse;
    }

    @Override
    public ApiResponse deleteCategory(Long id) {
        ApiResponse apiResponse = new ApiResponse("false");

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        categoryRepository.delete(category);
        apiResponse = new ApiResponse("true");

        return apiResponse;
    }

    @Override
    public org.springframework.data.domain.Page<Category> getCategoriesByPages(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }
}
