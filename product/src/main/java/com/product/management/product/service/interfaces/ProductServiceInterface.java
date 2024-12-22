package com.product.management.product.service.interfaces;

import com.product.management.product.dtos.ApiResponse;
import com.product.management.product.dtos.CategoryCreateDTO;
import com.product.management.product.dtos.ProductCreateDTO;
import com.product.management.product.model.Category;
import com.product.management.product.model.Product;

public interface ProductServiceInterface {
	
	org.springframework.data.domain.Page<Product> getProductByPages(int page, int size); 
	
    Product getProductById(Long id);

    ApiResponse createProduct(ProductCreateDTO productCreateDTO);
    
    ApiResponse updateProduct(Long id, ProductCreateDTO ProductCreateDTO);
    
    ApiResponse deleteProduct(Long id);
    
    

    
}
