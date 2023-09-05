package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.FakeStoreProductDto;
import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl="https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl="https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    public GenericProductDto createProduct(GenericProductDto product){

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response=restTemplate.postForEntity(createProductRequestUrl,product,GenericProductDto.class);
        return response.getBody();
    }
    @Override
    public GenericProductDto getProductById(Long id){

        RestTemplate restTemplate=restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response=restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto= response.getBody();
        GenericProductDto product=new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
//        response.getStatusCode();

        return product;
//        return null;
    }
}
