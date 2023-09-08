package dev.devesh.productservice.controllers;

import dev.devesh.productservice.dtos.ExceptionDto;
import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;
import dev.devesh.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

//    @Autowired
    //field injection
    private ProductService productService;

    //constructor injection
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService=productService;
    }

    //setter injection
//    @Autowired
//    public void setProductService(ProductService productService){
//        this.productService=productService;
//    }

    //GET/products{}
    @GetMapping
    public List<GenericProductDto> getAllProducts(){

//        return List.of(new GenericProductDto(),
//        new GenericProductDto());

        return productService.getAllProducts();
    }

    //localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {

        return productService.getProductById(id);
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
    public void updateProductById(){

    }

}
