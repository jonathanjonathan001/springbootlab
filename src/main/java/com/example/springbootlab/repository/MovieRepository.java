package com.example.springbootlab.repository;

import com.example.springbootlab.entity.Movie;
import org.springframework.data.repository.ListCrudRepository;

public interface MovieRepository extends ListCrudRepository<Movie,Long> {

}
