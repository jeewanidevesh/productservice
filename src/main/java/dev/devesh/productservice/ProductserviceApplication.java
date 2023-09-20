package dev.devesh.productservice;

import dev.devesh.productservice.inheritancedemo.joinedtable.MentorRepository;
import dev.devesh.productservice.inheritancedemo.joinedtable.UserRepository;
import dev.devesh.productservice.models.Category;
import dev.devesh.productservice.models.Price;
import dev.devesh.productservice.models.Product;
import dev.devesh.productservice.repositories.CategoryRepository;
import dev.devesh.productservice.repositories.PriceRepository;
import dev.devesh.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private MentorRepository mentorRepository;
    private UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public ProductserviceApplication(@Qualifier("jt_mr") MentorRepository mentorRepository,
                                     @Qualifier("jt_ur") UserRepository userRepository,
                                     ProductRepository productRepository,
                                     CategoryRepository categoryRepository,
                                     PriceRepository priceRepository) {
        this.mentorRepository = mentorRepository;
        this.userRepository=userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Mentor mentor=new Mentor();
//        mentor.setName("Devesh");
//        mentor.setEmail("devesh@gmail.com");
//        mentor.setAverageRating(4.65);
//        mentorRepository.save(mentor);
//
//        User user=new User();
//        user.setName("Sarath");
//        user.setEmail("sarath@gmail.com");
//        userRepository.save(user);
//
//        List<User> users=userRepository.findAll();
//
//        for(User user1:users){
//            System.out.println(user1);
//        }

        Category category=new Category();
        category.setName("Apple Devices");
        Category savedCategory = categoryRepository.save(category);

        Price price=new Price();
        Price savedPrice = priceRepository.save(price);

        Product product=new Product();
        product.setTitle("iPhone 15 Pro");
        product.setDescription("The best iPhone Ever");
        product.setCategory(savedCategory);
        product.setPrice(savedPrice);
        productRepository.save(product);

//        Category category1=categoryRepository.findById(
//                UUID.fromString("618ca4b8-6850-47b2-94e0-534500c543e0")).get();
//        System.out.println("Category name is "+ category1.getName());
//
//        System.out.println("Printing all products in the category");
//        Thread.sleep(1000);

//        category1.getProducts().forEach(
//                product1 -> System.out.println(product1.getTitle())
//        );

//        for(Product product1:category1.getProducts()){
//            try{
//                System.out.println(product1.getTitle());
//            }
//            catch (Exception e){
//                System.out.println(e.getMessage());
//            }
//        }


    }
}
