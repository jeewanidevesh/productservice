package dev.devesh.productservice.inheritancedemo.tableperclass;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name="userId3", initialValue=1)
    private Long id;
    private String name;
    private String email;
}