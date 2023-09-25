package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.dtos.ProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductServiceApis {

    //getAllProducts
    List<ProductDto>getAllProducts();
    //getAllCategories
    List<String> getAllCategories();
    //getProductById
    ProductDto getProductById(String id) throws NotFoundException;
    //getProductByCategory
    List<ProductDto> getProductsByCategory(String categoryId) throws NotFoundException;
    //addonProduct
    ProductDto addonProduct(ProductDto productDto);
    //updateProduct
    ProductDto updateProduct(ProductDto productDto , String id) throws NotFoundException;
    //deleteProduct
    ProductDto deleteProduct(String id) throws NotFoundException;
}
