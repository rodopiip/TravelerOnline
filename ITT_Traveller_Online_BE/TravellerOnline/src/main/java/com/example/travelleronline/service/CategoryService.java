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
    //todo
//    public List<CategoryDTO> getAllCategories() {
//        List<CategoryDTO> categoryDTOs = categoryRepository.getAll().stream()
//                .map(category -> (category, CategoryDTO.class))
//    }
    //note: admins only
    public void deleteCategory(int categoryId) {
        return;
    }//todo
    //note: admins only
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = toCategory(categoryDTO);
        //try{
        Category savedCategory = categoryRepository.save(category);
        //}catch(){}//todo
        return toCategoryDTO(savedCategory);
    }
    public Category getByCategoryId(int id){
        Category category = categoryRepository.getById(id);
        return category;
    }

    public List<CategoryDTO> createCategories(List<CategoryDTO> categoryDTOs) {
        List <Category> categories = categoryDTOs.stream()
                        .map(categoryDTO -> toCategory(categoryDTO))
                        .collect(Collectors.toList());
//        try{
            List <Category> savedCategories = categoryRepository.saveAll(categories);
//        }catch (){}//todo
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
}
