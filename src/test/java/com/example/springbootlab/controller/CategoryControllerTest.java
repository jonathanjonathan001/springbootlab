package com.example.springbootlab.controller;

import com.example.springbootlab.entity.Category;
import com.example.springbootlab.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CategoryRepository categoryRepository;

    Category category1 = new Category();
    Category category2 = new Category();

    @Test
    void shouldCreateCategories() throws Exception{

        category1.setId(1L);
        category1.setName("Comedy");


        mockMvc.perform(post("/categories")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(category1)))
                .andExpect(status().isOk());


    }

    @Test
    void shouldFindAllCategories() throws Exception{

        category1.setId(1L);
        category1.setName("Action");

        category2.setId(2L);
        category2.setName("Comedy");

        when(categoryRepository.findAll()).thenReturn(List.of(category1,category2));

        mockMvc.perform(get("/categories")).andExpect(status().isOk()).andDo(print());

    }

    @Test
    void shouldFindOneCategory() throws Exception {

        category1.setId(1L);
        category1.setName("Action");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category1));

        mockMvc.perform(get("/categories/1")).andExpect(status().isOk()).andDo(print());

    }

    @Test
    void shouldUpdateOneCategory() throws Exception {

        category2.setId(2L);
        category2.setName("Comedy");

        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category2));


        mockMvc.perform(put("/categories/{id}", 2L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(category2)))
                        .andExpect(status().isOk()).andDo(print());


    }

    @Test
    void shouldDeleteCategory() throws Exception {

        category2.setId(2L);
        category2.setName("Comedy");

        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category2));


        mockMvc.perform(delete("/categories/{id}", 2L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(category2)))
                .andExpect(status().isOk()).andDo(print());




    }





}