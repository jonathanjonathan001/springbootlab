package com.example.springbootlab.repository;

import com.example.springbootlab.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends ListCrudRepository<Category,Long> {

    @EntityGraph(value = "Category.movie")
    Optional<Category> findById(Long id);

    @EntityGraph(value = "Category.movie")
    List<Category> findAll();
}
