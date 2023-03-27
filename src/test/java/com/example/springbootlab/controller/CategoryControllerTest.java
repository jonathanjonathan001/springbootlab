package com.example.springbootlab.controller;

import com.example.springbootlab.entity.Category;
import com.example.springbootlab.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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





}