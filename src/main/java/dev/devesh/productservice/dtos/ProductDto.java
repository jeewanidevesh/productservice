package dev.devesh.productservice.dtos;

import dev.devesh.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductDto {

    private UUID id;
    private String title;
    private String description;
    private String image;
    private CategoryDto category;
    private String currency;
    private double price;

    //    private String title;

//    private String description;
//
//    private String image;
//    //            P : C
//    // => L to R: 1 : 1
//    // => R to L: m : 1
//    // => Ans:    m : 1
//    private Price price;
}
