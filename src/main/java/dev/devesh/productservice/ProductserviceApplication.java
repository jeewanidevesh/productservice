package dev.devesh.productservice;

import dev.devesh.productservice.inheritancedemo.joinedtable.MentorRepository;
import dev.devesh.productservice.inheritancedemo.joinedtable.Mentor;
import dev.devesh.productservice.inheritancedemo.joinedtable.User;
import dev.devesh.productservice.inheritancedemo.joinedtable.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private MentorRepository mentorRepository;
    private UserRepository userRepository;

    public ProductserviceApplication(@Qualifier("jt_mr") MentorRepository mentorRepository,
                                     @Qualifier("jt_ur") UserRepository userRepository) {
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

        List<User> users=userRepository.findAll();

        for(User user1:users){
            System.out.println(user1);
        }
    }
}
