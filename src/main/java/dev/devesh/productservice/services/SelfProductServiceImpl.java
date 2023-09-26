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
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Primary
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
        return productRepository.getAllProductCategory();
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
    @Transactional
    @Override
    public List<ProductDto> getProductsByCategory(String categoryName) throws NotFoundException {
        List<Product> products=productRepository.getAllProductByCategory(categoryName);

        if(products==null || products.isEmpty()){
            throw new NotFoundException("Category " +categoryName+" Not found");
        }

        return products.stream().map(this::convertProductToGenericProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto addonProduct(ProductDto productDto) {
        Product product=convertProductDtoToProduct(productDto);
        product=productRepository.save(product);
        return convertProductToGenericProductDto(product);
    }

    @Transactional
    @Override
    public ProductDto updateProduct(ProductDto productDto, String id) throws NotFoundException {
        //retrieve the existing product by id
        Optional<Product> optionalProduct=productRepository.findById(UUID.fromString(id));

        if(optionalProduct.isPresent()){
            Product existingProduct=optionalProduct.get();
            //update the properties of product based on DTO
            existingProduct.setTitle(productDto.getTitle());
            existingProduct.setDescription(productDto.getDescription());

            //update the product price
            Price price=existingProduct.getPrice();

            if(price==null){
                price=new Price();
            }
            price.setCurrency(productDto.getCurrency());
            price.setPrice(productDto.getPrice());
            existingProduct.setPrice(price);

            existingProduct.setImage(productDto.getImage());

            CategoryDto categoryDto= productDto.getCategory();
            Category category=null;

            //check if the category already exist by name
            if(categoryDto!=null && categoryDto.getName()!=null){
                category=categoryRepository.findByName(categoryDto.getName());
            }

            //check if category doesn't exist create a new one
            if(category==null){
                category=new Category();
                category.setName(categoryDto.getName());
            }
            //add the product to the category and set the category for the product
            category.getProducts().add(existingProduct);
            existingProduct.setCategory(category);

            //save the updated product
            existingProduct=productRepository.save(existingProduct);

            return convertProductToGenericProductDto(existingProduct);
        }
        else{
            throw new NotFoundException("Product with id"+ id+ " not found");
        }
    }

    @Transactional
    @Override
    public ProductDto deleteProduct(String id) throws NotFoundException {
        Product product=productRepository.findById(UUID.fromString(id)).orElse(null);
        if(product!=null){
            productRepository.deleteById(UUID.fromString(id));
            return convertProductToGenericProductDto(product);
        }
        else{
            throw new NotFoundException("Product with id" +id+" not found");
        }
    }
}
