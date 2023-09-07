package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(Long id);
    List<GenericProductDto> getAllProducts();

}
