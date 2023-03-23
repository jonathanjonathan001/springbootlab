package com.example.springbootlab.controller;

import com.example.springbootlab.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final MovieRepository movieRepository;

    public WebController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/showmovies")
    public String showMovies(Model model) {
        model.addAttribute(movieRepository.findAll());
        return "movies";
    }



}
