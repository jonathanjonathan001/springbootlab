package com.example.springbootlab.controller;


import com.example.springbootlab.entity.Category;
import com.example.springbootlab.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    void addCategory(@RequestBody Category category) {
        categoryRepository.save(category);

    }

    @GetMapping
    List<Category> getAllCategories() {
        return categoryRepository.findAll();

    }


    @GetMapping("/{id}")
    Optional<Category> getOneCategory(@PathVariable long id) {
        return Optional.of(categoryRepository.findById(id).orElseThrow());

    }


    @PutMapping("/{id}")
    Category updateCategory(@RequestBody Category category, @PathVariable long id) {
        var updateCategory = categoryRepository.findById(id).orElseThrow();
        updateCategory.setName(category.getName());
        updateCategory.setMovies(category.getMovies());

        return categoryRepository.save(updateCategory);

    }


    @DeleteMapping("/{id}")
    String deleteCategory(@PathVariable long id) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
            categoryRepository.delete(category);
            return "Category " + id + " was deleted!";
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }
    }

}