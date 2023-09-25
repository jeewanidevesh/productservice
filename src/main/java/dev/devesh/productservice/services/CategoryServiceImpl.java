package dev.devesh.productservice.services;

import dev.devesh.productservice.models.Category;
import dev.devesh.productservice.models.Product;
import dev.devesh.productservice.repositories.CategoryRepository;
import dev.devesh.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository){
        this.categoryRepository=categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Category getCategory(String uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));

        if(categoryOptional.isEmpty()){
            return null;
        }

        Category category=categoryOptional.get();
        List<Product> products=category.getProducts();
        return category;
    }

    public List<String> getProductTitles(List<String> categoryUUIDs){

        List<UUID> uuids=new ArrayList<>();

        for(String uuid:categoryUUIDs){
            uuids.add(UUID.fromString(uuid));
        }
//
        List<Category> categories=categoryRepository.findAllById(uuids);

        List<Product> products=productRepository.findAllByCategoryIn(categories);

        List<String> titles=new ArrayList<>();

        for(Product p:products){
            titles.add(p.getTitle());
        }

        return  titles;
//
////        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));
//
//        List<String> titles=new ArrayList<>();
//
//        categories.forEach(
//                category -> {
//                    category.getProducts().forEach(
//                            product -> {
//                                titles.add(product.getTitle());
//                            }
//                    );
//                }
//        );
////        category.getProducts().forEach(
////                product -> titles.add(product.getTitle())
////        );
////
////        List<Product> products=category.getProducts();
//        return titles;

    }


}
