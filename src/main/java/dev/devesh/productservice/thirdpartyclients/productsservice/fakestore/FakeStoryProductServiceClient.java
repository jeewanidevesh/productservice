package dev.devesh.productservice.thirdpartyclients.productsservice.fakestore;

import dev.devesh.productservice.dtos.FakeStoreProductDto;
import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * wrapper over fakeStore API
 *
 * implements ThirdPartyProductServiceClient --removed from this class
 */
@Service
public class FakeStoryProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;
//    private String specificProductRequestUrl ="https://fakestoreapi.com/products/{id}";
//    private String productRequestsBaseUrl ="https://fakestoreapi.com/products";

    @Value("${fakestore.api.url}")
    private  String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;

    private String specificProductRequestUrl;// =fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";
    private String productRequestsBaseUrl;// =fakeStoreApiUrl+fakeStoreProductsApiPath;

    public FakeStoryProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRequestsBaseUrl  = fakeStoreApiUrl + fakeStoreProductsApiPath;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
    }

    public FakeStoreProductDto createProduct(GenericProductDto product){

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response=
                restTemplate.postForEntity(productRequestsBaseUrl,product,FakeStoreProductDto.class);
        return response.getBody();
    }
//    @Override
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {

        RestTemplate restTemplate=restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response=
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto= response.getBody();

        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }


//        response.getStatusCode();

        return fakeStoreProductDto;
//        return null;
    }

//    @Override
    public List<FakeStoreProductDto> getAllProducts() {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response=
                restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);
        List<GenericProductDto> answer=new ArrayList<>();


        return Arrays.stream(response.getBody()).toList();
    }

//    @Override
    public FakeStoreProductDto deleteProduct(Long id)  {
        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback=restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor=
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> response=
                restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE,requestCallback,responseExtractor,id);


        return response.getBody();
    }

    public FakeStoreProductDto updateProductById(Long id, GenericProductDto product) {
        /***
         * This is second and simple method as taught in class
         */
        RestTemplate restTemplate=restTemplateBuilder.build();
        RequestCallback requestCallback=restTemplate.httpEntityCallback(product,GenericProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor=
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response=restTemplate.execute(
                this.specificProductRequestUrl,HttpMethod.PUT,requestCallback,responseExtractor,id);

        return response.getBody();
        /***
         *
         * this is first method second method I wrote above
         */
//        //String updateProductRequestUrl="https://fakestoreapi.com/products/{id}";
//        RestTemplate restTemplate=restTemplateBuilder.build();
//
//        HttpHeaders headers=new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<GenericProductDto> requestEntity=new HttpEntity<>(product,headers);
//
//        ResponseEntity<FakeStoreProductDto>  responseEntity=
//                restTemplate.exchange(
//                        this.specificProductRequestUrl,HttpMethod.PUT,requestEntity, FakeStoreProductDto.class,id);
//        return responseEntity.getBody();
    }
}
