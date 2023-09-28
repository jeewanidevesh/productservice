package dev.devesh.productservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends  BaseModel{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    private String title;
    private String description;
    private String image;
    //            P : C
    // => L to R: 1 : 1
    // => R to L: m : 1
    // => Ans:    m : 1
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category")
    private Category category;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn
    private Price price;
//    private int inventoryCount;


}
