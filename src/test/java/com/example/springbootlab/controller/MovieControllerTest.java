package com.example.springbootlab.controller;

import com.example.springbootlab.entity.Movie;
import com.example.springbootlab.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private MovieRepository repo;

    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void init() {
        movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("The Lord of the Rings");
        movie1.setYear(2001);

        movie2 = new Movie();
        movie2.setId(2L);
        movie2.setName("Transformers");
        movie2.setYear(2007);
    }


    @Test
    void shouldCreateNewMovie() throws Exception {

        when(repo.save(any(Movie.class))).thenReturn(movie1);

        mockMvc.perform(post("/movies")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(movie1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAllMoviesShouldReturnAllMoviesAnd200Ok() throws Exception {

        when(repo.findAll()).thenReturn(List.of(movie1, movie2));
        mockMvc.perform(get("/movies")).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getOneMovieShouldReturnOneMovieFromIdAnd200Ok() throws Exception {

        when(repo.findById(1L)).thenReturn(Optional.of(movie1));
        mockMvc.perform(get("/movies/1")).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldUpdateMovie() throws Exception {
        when(repo.findById(1L)).thenReturn(Optional.of(movie1));

        mockMvc.perform(put("/movies/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie1)))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void shouldDeleteMovie() throws Exception {

        when(repo.findById(1L)).thenReturn(Optional.of(movie1));
        mockMvc.perform(delete("/movies/{id}", 1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(movie1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
