package com.product.management.product.service.implementations;

import com.product.management.product.dtos.ApiResponse;
import com.product.management.product.dtos.ProductCreateDTO;
import com.product.management.product.exception.ProductNotFoundException;
import com.product.management.product.exception.CategoryNotFoundException;
import com.product.management.product.model.Category;
import com.product.management.product.model.Product;
import com.product.management.product.repository.CategoryRepository;
import com.product.management.product.repository.ProductRepository;
import com.product.management.product.service.interfaces.ProductServiceInterface;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductInterfaceImpl implements ProductServiceInterface {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public ApiResponse createProduct(ProductCreateDTO productCreateDTO) {
        ApiResponse apiResponse = new ApiResponse("false");

        boolean exists = productRepository.existsByNameIgnoreCase(productCreateDTO.getName());
        if (!exists) {
            Category category = categoryRepository.findById(productCreateDTO.getCategory())
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + productCreateDTO.getCategory()));
            
            Product product = modelMapper.map(productCreateDTO, Product.class);
            product.setCategory(category);
            productRepository.save(product);
            apiResponse = new ApiResponse("true");
        }

        return apiResponse;
    }

    @Override
    public ApiResponse updateProduct(Long id, ProductCreateDTO productCreateDTO) {
        ApiResponse apiResponse = new ApiResponse("false");

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        product.setName(productCreateDTO.getName());
        product.setPrice(productCreateDTO.getPrice());
        product.setDescription(productCreateDTO.getDescription());
        productRepository.save(product);

        apiResponse = new ApiResponse("true");
        return apiResponse;
    }

    @Override
    public ApiResponse deleteProduct(Long id) {
        ApiResponse apiResponse = new ApiResponse("false");

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        productRepository.delete(product);
        apiResponse = new ApiResponse("true");
        return apiResponse;
    }

    @Override
    public Page<Product> getProductByPages(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }
}
