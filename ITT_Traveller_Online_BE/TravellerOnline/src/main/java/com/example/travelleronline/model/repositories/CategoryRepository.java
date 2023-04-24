package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.DTOs.category.CategoryDTO;
import com.example.travelleronline.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category getById(int id);//todo for displaying the post
    @Query(value = "SELECT * FROM categories;", nativeQuery = true)
    public List<Category> getAll();

//    public List<Category> sa //todo
}