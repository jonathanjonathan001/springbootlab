package com.example.springbootlab.controller;


import com.example.springbootlab.entity.Category;
import com.example.springbootlab.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return categoryRepository.findById(id);

    }



    @PutMapping("/{id}")
    Category updateCategory(@RequestBody Category category, @PathVariable long id) {
        var updateCategory = categoryRepository.findById(id).orElseThrow(() -> new NullPointerException("That CategoryId does not exist!" ));
        updateCategory.setName(category.getName());
        updateCategory.setMovies(category.getMovies());

        return categoryRepository.save(updateCategory);

    }

    @DeleteMapping("/{id}")
    String deleteCategory(@PathVariable long id) {

            categoryRepository.deleteById(id);
            return "Category " + id + " was deleted!";
    }


}
