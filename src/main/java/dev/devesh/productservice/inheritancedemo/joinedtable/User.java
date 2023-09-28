package dev.devesh.productservice.inheritancedemo.joinedtable;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    @SequenceGenerator(name="userId1", initialValue=1)
    private Long id;
    private String name;
    private String email;
}
