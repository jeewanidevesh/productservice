package dev.devesh.productservice.controllers;

import dev.devesh.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping
    public void getAllProducts(){

    }

    //localhost:8080/products/123
    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id){

        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(){

    }

    @PostMapping
    public String createProduct(){
        return "Created new product with id : "+ UUID.randomUUID();
    }

    @PutMapping("{id}")
    public void updateProductById(){

    }

}
