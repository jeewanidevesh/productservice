package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(Long id) throws NotFoundException;
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProduct(Long id) throws NotFoundException;

    GenericProductDto updateProduct(Long id,GenericProductDto product);



}
