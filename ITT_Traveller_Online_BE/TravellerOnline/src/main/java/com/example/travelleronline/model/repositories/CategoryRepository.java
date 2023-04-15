package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}