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
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductServiceApis{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    private GenericProductDto convertProductToGenericProductDto(Product product){
        GenericProductDto productDto=new GenericProductDto();
//        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        Price price=product.getPrice();
        productDto.setPrice(price.getPrice());
//        productDto.setCurrency(price.getCurrency());
        productDto.setImage(product.getImage());
        Category category=product.getCategory();
        List<Product> products=category.getProducts();
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setUuid(category.getUuid());
        categoryDto.setName(category.getName());
        productDto.setCategory(category.getName());

        return productDto;
    }

    private Product convertProductDtoToProduct(GenericProductDto productDto){
        Product product=new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        Price price=new Price();
        price.setPrice(productDto.getPrice());
        price.setCurrency("INR");
        product.setPrice(price);
        product.setImage(productDto.getImage());
        String categoryDto=productDto.getCategory();
        Category category=null;
        if(categoryDto!=null){
            category=categoryRepository.findByName(categoryDto);
        }
        if(category==null){
            category=new Category();
            category.setName(categoryDto);
        }

        category.getProducts().add(product);
        product.setCategory(category);

        return product;
    }


    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products=productRepository.findAll().stream()
                .collect(Collectors.toList());
        List<GenericProductDto> productDtos=new ArrayList<>();

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
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        Product product=productRepository.findById(id).orElse(null);
        if(product!=null){
            return convertProductToGenericProductDto(product);
        }
        else{
            throw new NotFoundException("Product with id "+ id+" not found");
        }
    }
    @Transactional
    @Override
    public List<GenericProductDto> getProductsByCategory(String categoryName) throws NotFoundException {
        List<Product> products=productRepository.getAllProductByCategory(categoryName);

        if(products==null || products.isEmpty()){
            throw new NotFoundException("Category " +categoryName+" Not found");
        }

        return products.stream().map(this::convertProductToGenericProductDto).collect(Collectors.toList());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto productDto) {
        Product product=convertProductDtoToProduct(productDto);
        product=productRepository.save(product);
        return convertProductToGenericProductDto(product);
    }

    @Transactional
    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto, Long id) throws NotFoundException {
        //retrieve the existing product by id
        Optional<Product> optionalProduct=productRepository.findById(id);

        if(optionalProduct.isPresent()){
            Product existingProduct=optionalProduct.get();
            //update the properties of product based on DTO
            existingProduct.setTitle(genericProductDto.getTitle());
            existingProduct.setDescription(genericProductDto.getDescription());

            //update the product price
            Price price=existingProduct.getPrice();

            if(price==null){
                price=new Price();
            }
            price.setCurrency("INR");
            price.setPrice(genericProductDto.getPrice());
            existingProduct.setPrice(price);

            existingProduct.setImage(genericProductDto.getImage());

            String categoryDto= genericProductDto.getCategory();
            Category category=null;

            //check if the category already exist by name
            if(categoryDto!=null ){
                category=categoryRepository.findByName(categoryDto);
            }

            //check if category doesn't exist create a new one
            if(category==null){
                category=new Category();
                category.setName(categoryDto);
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
    public GenericProductDto deleteProduct(Long id) throws NotFoundException {
        Product product=productRepository.findById(id).orElse(null);
        if(product!=null){
            productRepository.deleteById(id);
            return convertProductToGenericProductDto(product);
        }
        else{
            throw new NotFoundException("Product with id" +id+" not found");
        }
    }
}
