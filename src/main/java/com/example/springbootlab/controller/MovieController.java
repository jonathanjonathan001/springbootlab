package com.example.springbootlab.controller;


import com.example.springbootlab.entity.Movie;
import com.example.springbootlab.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    MovieRepository repo;

    public MovieController(MovieRepository movieRepository) {
        repo = movieRepository;
    }

    @PostMapping
    void addMovie(@RequestBody Movie movie) {
        repo.save(movie);
    }

    @GetMapping
    List<Movie> getAllMovies() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    Optional<Movie> getOneMovie(@PathVariable long id) {
        return repo.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody Movie movie) {
        Movie updateMovie = repo.findById(id).orElseThrow(() -> new ResolutionException("Movie not exist with id: " + id));
        updateMovie.setName(movie.getName());
        updateMovie.setYear(movie.getYear());

        repo.save(updateMovie);
        return ResponseEntity.ok(updateMovie);
    }

    @DeleteMapping(value = "/{id}")
    void deleteMovieById(@PathVariable Movie id) {
        repo.delete(id);
    }

}
