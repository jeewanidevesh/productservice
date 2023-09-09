package dev.devesh.productservice.thirdpartyclients.productsservice;

import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyProductServiceClient {
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(Long id) throws NotFoundException;
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProduct(Long id) throws NotFoundException;
}
