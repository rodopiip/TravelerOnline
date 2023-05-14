package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.category.CategoryDTO;
import com.example.travelleronline.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category Controller", description = "Category endpoints")
@RestController
public class CategoryController extends AbstractController{
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/categories/{categoryId}")
    public CategoryDTO getAllCategories(@PathVariable("categoryId") int categoryId) {
        return categoryService.getByCategoryId(categoryId);
    }
    @PostMapping("/categories")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO returnedCategoryDTO = categoryService.createCategory(categoryDTO);
        logger.info(returnedCategoryDTO.toString() + " category to be created...");
        return returnedCategoryDTO;
    }
    @PostMapping("/categories/add-many")
    public List<CategoryDTO> createCategories(@RequestBody List<CategoryDTO> categories){
        return categoryService.createCategories(categories);
    }

}