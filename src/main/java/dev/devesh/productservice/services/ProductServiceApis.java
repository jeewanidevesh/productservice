package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.dtos.ProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductServiceApis {

    //getAllProducts
    List<GenericProductDto>getAllProducts();
    //getAllCategories
    List<String> getAllCategories();
    //getProductById
    GenericProductDto getProductById(Long id) throws NotFoundException;
    //getProductByCategory
    List<GenericProductDto> getProductsByCategory(String categoryId) throws NotFoundException;
    //addonProduct
    GenericProductDto createProduct(GenericProductDto genericProductDto);
    //updateProduct
    GenericProductDto updateProduct(GenericProductDto genericProductDto , Long id) throws NotFoundException;
    //deleteProduct
    GenericProductDto deleteProduct(Long id) throws NotFoundException;
}
