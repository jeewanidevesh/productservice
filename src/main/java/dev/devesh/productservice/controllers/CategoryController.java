package dev.devesh.productservice.controllers;

import dev.devesh.productservice.dtos.ProductDto;
import dev.devesh.productservice.models.Category;
import dev.devesh.productservice.models.Product;
import dev.devesh.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/{uuid}")
    public List<ProductDto> getCategory(@PathVariable("uuid") String uuid){
        List<Product> products=categoryService.getCategory(uuid).getProducts();
        List<ProductDto> productDtos=new ArrayList<>();

        for(Product product: products) {
            ProductDto productDto = new ProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setTitle(product.getTitle());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            productDtos.add(productDto);

        }
        return productDtos;
    }

}
