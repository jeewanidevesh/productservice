package dev.devesh.productservice.thirdpartyclients.productsservice;

import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;

import java.util.List;


/**
 *Since Fake Store Product DTO has been shifted from DTO folder to third party client for proper third party
 * implementation so technically no need of ThirdPartyProductServiceClient.java file. It was just for understanding
 * purpose
 */
public interface ThirdPartyProductServiceClient {
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(Long id) throws NotFoundException;
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProduct(Long id) throws NotFoundException;
}
