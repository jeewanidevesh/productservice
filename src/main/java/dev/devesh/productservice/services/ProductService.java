package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.models.Product;

public interface ProductService {

    GenericProductDto getProductById(Long id);

}
