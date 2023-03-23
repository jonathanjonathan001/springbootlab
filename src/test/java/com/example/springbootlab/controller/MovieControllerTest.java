package com.example.springbootlab.controller;

import com.example.springbootlab.entity.Movie;
import com.example.springbootlab.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieRepository repo;


    @Test
    void getAllMoviesShouldReturnAllMoviesAnd200Ok() throws Exception {
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("The Lord of the Rings");
        movie1.setYear(2001);
        Movie movie2 = new Movie();
        movie1.setId(2L);
        movie1.setName("Transformers");
        movie1.setYear(2007);

        when(repo.findAll()).thenReturn(List.of(movie1,movie2));
        mockMvc.perform(get("/movies")).andExpect(status().isOk());

    }

    @Test
    void getOneMovieShouldReturnOneMovieFromIdAnd200Ok() throws Exception {
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("Sagan om ringen");
        movie1.setYear(2001);
        when(repo.findById(1L)).thenReturn(Optional.of(movie1));
        mockMvc.perform(get("/movies/1")).andExpect(status().isOk());

    }


}
