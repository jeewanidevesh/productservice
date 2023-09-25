package dev.devesh.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CategoryDto {

    private UUID uuid;
    private String name;

    private List<ProductDto> products;
}
