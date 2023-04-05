package com.example.springbootlab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Entity
@Getter
@Setter
@NamedEntityGraph(name = "Category.movie",
        attributeNodes = @NamedAttributeNode("movies"))
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="category", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Movie> movies;

}
