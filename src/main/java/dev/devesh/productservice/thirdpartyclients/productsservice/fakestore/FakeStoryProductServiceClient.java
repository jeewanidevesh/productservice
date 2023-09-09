package dev.devesh.productservice.thirdpartyclients.productsservice.fakestore;

import dev.devesh.productservice.dtos.FakeStoreProductDto;
import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;
import dev.devesh.productservice.thirdpartyclients.productsservice.ThirdPartyProductServiceClient;
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


@Service
public class FakeStoryProductServiceClient implements ThirdPartyProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestUrl ="https://fakestoreapi.com/products/{id}";
    private String productRequestsBaseUrl ="https://fakestoreapi.com/products";

    public FakeStoryProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
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

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response=
                restTemplate.postForEntity(productRequestsBaseUrl,product,GenericProductDto.class);
        return response.getBody();
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {

        RestTemplate restTemplate=restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response=
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto= response.getBody();

        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }


//        response.getStatusCode();

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
//        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response=
                restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);
        List<GenericProductDto> answer=new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()){

            answer.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }

        return answer;
    }

    @Override
    public GenericProductDto deleteProduct(Long id)  {
        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback=restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor=
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> response=
                restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE,requestCallback,responseExtractor,id);

        FakeStoreProductDto fakeStoreProductDto= response.getBody();


        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }
}
