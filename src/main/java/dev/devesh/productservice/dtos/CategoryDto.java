package dev.devesh.productservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto {

    private UUID uuid;
    private String name;
    private List<ProductDto> products;
}
