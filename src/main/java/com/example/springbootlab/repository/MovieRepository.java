package com.example.springbootlab.repository;

import com.example.springbootlab.entity.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends ListCrudRepository<Movie,Long> {


    @EntityGraph(value = "Movie.category")
    Optional<Movie> findById(Long id);
    @EntityGraph(value = "Movie.category")
    List<Movie> findAll();
}
