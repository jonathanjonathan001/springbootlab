package com.example.springbootlab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NamedEntityGraph(name = "Movie.category",
        attributeNodes = @NamedAttributeNode("category"))
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int year;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
