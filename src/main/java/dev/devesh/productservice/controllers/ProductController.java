package dev.devesh.productservice.controllers;

import dev.devesh.productservice.dtos.ExceptionDto;
import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;
import dev.devesh.productservice.security.JwtObject;
import dev.devesh.productservice.security.TokenValidator;
import dev.devesh.productservice.services.ProductService;
import dev.devesh.productservice.services.ProductServiceApis;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

//    @Autowired
    //field injection
    private ProductService productService;
    private TokenValidator tokenValidator;

    //constructor injection
//    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
//        this.productService=productService;
//    }

    public ProductController(ProductService productService,TokenValidator tokenValidator){
        this.productService=productService;
        this.tokenValidator=tokenValidator;
    }

    //setter injection
//    @Autowired
//    public void setProductService(ProductService productService){
//        this.productService=productService;
//    }

    //GET/products{}
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        List<GenericProductDto> productDtos = productService.getAllProducts();
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(
                    productDtos,
                    HttpStatus.NOT_FOUND
            );
        }

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (GenericProductDto gpd: productDtos) {
            genericProductDtos.add(gpd);
        };

//        genericProductDtos.remove(genericProductDtos.get(0));

        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);

//        productDtos.get(0).setId(1001L);
//
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
//    public List<GenericProductDto> getAllProducts(){
//
////        return List.of(new GenericProductDto(),
////        new GenericProductDto());
//
//        return productService.getAllProducts();
//    }

    //localhost:8080/products/123
    @GetMapping("{id}")
    //authorization
    public GenericProductDto getProductById(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("id") Long id) throws NotFoundException {

        //put this in method signature "HttpServletRequest request" for implementation
//        request.getRemoteAddr();
        System.out.println(authToken);

        Optional<JwtObject> authTokenObjOptional;
        JwtObject authTokenObj=null;

        if(authToken!=null){
            authTokenObjOptional=tokenValidator.validateToken(authToken);
            if(authTokenObjOptional.isEmpty()){
                //ignore
            }
            authTokenObj=authTokenObjOptional.get();
        }

        GenericProductDto productDto=productService.getProductById(id, authTokenObj.getUserId());
//        GenericProductDto productDto=new GenericProductDto();
        if(productDto==null){
            throw new NotFoundException("Product doesn't Exist");
//            throw new NotFoundException( "not found this "+id);
        }

//        return productService.getProductById(id);
        return productDto;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id")Long id) throws NotFoundException {

        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
        //return productService.deleteProduct(id);
    }

//    @ExceptionHandler(NotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//
//        return new ResponseEntity(
//                new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),HttpStatus.NOT_FOUND);
////        System.out.println("Not found exception happened");
//    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
//        System.out.println(product.name);
//        return "Created new product with id : "+ UUID.randomUUID();
//        return "Created new product with name :"+product.getTitle();
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id")Long id,@RequestBody GenericProductDto product) throws NotFoundException {

        return productService.updateProduct(id,product);
    }

}
