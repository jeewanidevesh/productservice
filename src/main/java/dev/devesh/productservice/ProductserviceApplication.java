package dev.devesh.productservice;

import dev.devesh.productservice.inheritancedemo.singletable.Mentor;
import dev.devesh.productservice.inheritancedemo.singletable.MentorRepository;
import dev.devesh.productservice.inheritancedemo.singletable.User;
import dev.devesh.productservice.inheritancedemo.singletable.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private MentorRepository mentorRepository;
    private UserRepository userRepository;

    public ProductserviceApplication(MentorRepository mentorRepository,UserRepository userRepository) {
        this.mentorRepository = mentorRepository;
        this.userRepository=userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Mentor mentor=new Mentor();
        mentor.setName("Devesh");
        mentor.setEmail("devesh@gmail.com");
        mentor.setAverageRating(4.65);
        mentorRepository.save(mentor);

        User user=new User();
        user.setName("Sarath");
        user.setEmail("sarath@gmail.com");
        userRepository.save(user);

    }
}
