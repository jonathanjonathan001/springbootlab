package com.example.springbootlab.controller;


import com.example.springbootlab.entity.Movie;
import com.example.springbootlab.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

MovieRepository repo;

public MovieController(MovieRepository movieRepository ){
 repo = movieRepository;
}

@PostMapping
    void addMovie (@RequestBody Movie movie){
    repo.save(movie);
}

    @GetMapping
    List<Movie> getAllMovies(){
        return repo.findAll();
    }
}
