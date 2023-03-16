package com.example.springbootlab.repository;

import com.example.springbootlab.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category,Long> {
}
