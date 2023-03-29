package com.example.springbootlab.controller;


import com.example.springbootlab.entity.Movie;
import com.example.springbootlab.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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
        return Optional.of(repo.findById(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody Movie movie) {
        Movie updateMovie = repo.findById(id).orElseThrow();
        updateMovie.setName(movie.getName());
        updateMovie.setYear(movie.getYear());
        updateMovie.setCategory(movie.getCategory());
        repo.save(updateMovie);
        return ResponseEntity.ok(updateMovie);
    }

    @DeleteMapping(value = "/{id}")
    void deleteMovieById(@PathVariable Long id) {
        repo.deleteById(id);
    String deleteMovieById(@PathVariable long id) {
        try {
            Movie movie = repo.findById(id).orElseThrow(NoSuchElementException::new);
            repo.delete(movie);
            return "Movie " + id + " was deleted!";
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }
    }
}

