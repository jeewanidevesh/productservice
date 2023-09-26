package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.FakeStoreProductDto;
import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;
import dev.devesh.productservice.thirdpartyclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoryProductServiceClient fakeStoryProductServiceClient;

    public FakeStoreProductService(FakeStoryProductServiceClient fakeStoryProductServiceClient){
        this.fakeStoryProductServiceClient=fakeStoryProductServiceClient;
    }
    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product=new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }

    public GenericProductDto createProduct(GenericProductDto product){

        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.createProduct(product));
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto: fakeStoryProductServiceClient.getAllProducts()) {
            genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }
        return genericProductDtos;

//        return fakeStoryProductServiceClient.getAllProducts();
    }

    @Override
    public GenericProductDto deleteProduct(Long id)  {

        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.deleteProduct(id));
    }

    @Override
    public GenericProductDto updateProduct(Long id, GenericProductDto product) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.updateProductById(id, product));
    }


}
