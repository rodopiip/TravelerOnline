package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category getById(int id);
    @Query(value = "SELECT * FROM categories;", nativeQuery = true)
    public List<Category> getAll();
    @Query(value = "SELECT id FROM categories WHERE name LIKE CONCAT('%', :searchPrompt, '%') LIMIT 1;", nativeQuery = true)
    public Integer findCategoryIdByName(@Param("searchPrompt") String searchPrompt);
}