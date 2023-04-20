package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.category.CategoryDTO;
import com.example.travelleronline.model.entities.Category;
import com.example.travelleronline.model.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService extends AbstractService{
    @Autowired
    private CategoryRepository categoryRepository;
    public List<CategoryDTO> getAllCategories() {
        return null;
    }

    public void deleteCategory(int categoryId) {
        return;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return null;
    }
    public Category getByCategoryId(int id){
        return categoryRepository.getById(id);
    }
}
