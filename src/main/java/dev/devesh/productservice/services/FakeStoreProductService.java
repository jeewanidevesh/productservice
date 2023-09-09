package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.FakeStoreProductDto;
import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;
import dev.devesh.productservice.thirdpartyclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoryProductServiceClient fakeStoryProductServiceClient;

    public FakeStoreProductService(FakeStoryProductServiceClient fakeStoryProductServiceClient){
        this.fakeStoryProductServiceClient=fakeStoryProductServiceClient;
    }

    public GenericProductDto createProduct(GenericProductDto product){

        return fakeStoryProductServiceClient.createProduct(product);
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return fakeStoryProductServiceClient.getProductById(id);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        return fakeStoryProductServiceClient.getAllProducts();
    }

    @Override
    public GenericProductDto deleteProduct(Long id)  {

        return fakeStoryProductServiceClient.deleteProduct(id);
    }


}
