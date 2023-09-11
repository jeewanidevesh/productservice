package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;
import dev.devesh.productservice.thirdpartyclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.springframework.stereotype.Service;

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
