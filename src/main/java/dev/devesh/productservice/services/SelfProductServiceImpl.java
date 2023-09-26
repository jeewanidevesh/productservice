package dev.devesh.productservice.services;

import dev.devesh.productservice.dtos.CategoryDto;
import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.dtos.ProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;
import dev.devesh.productservice.models.Category;
import dev.devesh.productservice.models.Price;
import dev.devesh.productservice.models.Product;
import dev.devesh.productservice.repositories.CategoryRepository;
import dev.devesh.productservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
//@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductServiceApis{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    private ProductDto convertProductToGenericProductDto(Product product){
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getUuid());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        Price price=product.getPrice();
        productDto.setPrice(price.getPrice());
        productDto.setCurrency(price.getCurrency());
        productDto.setImage(product.getImage());
        Category category=product.getCategory();
        List<Product> products=category.getProducts();
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setUuid(category.getUuid());
        categoryDto.setName(category.getName());
        productDto.setCategory(categoryDto);

        return productDto;
    }

    private Product convertProductDtoToProduct(ProductDto productDto){
        Product product=new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        Price price=new Price();
        price.setPrice(productDto.getPrice());
        price.setCurrency(productDto.getCurrency());
        product.setPrice(price);
        product.setImage(productDto.getImage());
        CategoryDto categoryDto=productDto.getCategory();
        Category category=null;
        if(categoryDto!=null && categoryDto.getName()!=null){
            category=categoryRepository.findByName(categoryDto.getName());
        }
        if(category==null){
            category=new Category();
            category.setName(categoryDto.getName());
        }

        category.getProducts().add(product);
        product.setCategory(category);

        return product;
    }


    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products=productRepository.findAll().stream()
                .collect(Collectors.toList());
        List<ProductDto> productDtos=new ArrayList<>();

        for(Product product: products){
            productDtos.add(convertProductToGenericProductDto(product));
        }
        return productDtos;
    }

    @Override
    public List<String> getAllCategories() {
        return null;
    }

    @Override
    public ProductDto getProductById(String id) throws NotFoundException {
        Product product=productRepository.findById(UUID.fromString(id)).orElse(null);
        if(product!=null){
            return convertProductToGenericProductDto(product);
        }
        else{
            throw new NotFoundException("Product with id "+ id+" not found");
        }
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryName) throws NotFoundException {
        List<Product> products=productRepository.getAllProductByCategory(categoryName);



        return null;
    }

    @Override
    public ProductDto addonProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String id) throws NotFoundException {
        return null;
    }

    @Override
    public ProductDto deleteProduct(String id) throws NotFoundException {
        return null;
    }
}
