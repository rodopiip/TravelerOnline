package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.category.CategoryDTO;
import com.example.travelleronline.model.entities.Category;
import com.example.travelleronline.model.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService extends AbstractService{
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = toCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return toCategoryDTO(savedCategory);
    }
    public CategoryDTO getByCategoryId(int id){
        Category category = categoryRepository.getById(id);
        return mapper.map(category,CategoryDTO.class);
    }
    public List<CategoryDTO> createCategories(List<CategoryDTO> categoryDTOs) {
        List <Category> categories = categoryDTOs.stream()
                        .map(categoryDTO -> toCategory(categoryDTO))
                        .collect(Collectors.toList());
            List <Category> savedCategories = categoryRepository.saveAll(categories);
        return savedCategories.stream()
                .map(category -> toCategoryDTO(category))
                .collect(Collectors.toList());
    }
    private Category toCategory(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        return category;
    }
    private CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.getAll().stream()
                .map(category -> mapper.map(category,CategoryDTO.class))
                .collect(Collectors.toList());
    }
}
