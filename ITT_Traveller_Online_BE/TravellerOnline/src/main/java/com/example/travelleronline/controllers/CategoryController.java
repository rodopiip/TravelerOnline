package com.example.travelleronline.controllers;

import com.example.travelleronline.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("localhost:3333/categories")
public class CategoryController extends AbstractController{
    @Autowired
    private CategoryService categoryService;

    /*
    //get category - localhost:3333/categories/{category-id}
    //question: is this endpoint necessary?
    @GetMapping("/{categoryId}")
    public CategoryDTO getCategory(@PathVariable("categoryId") int categoryId) {
        return categoryService.getCategoryById(categoryId);//todo
    }
     */

    //get all categories - localhost:3333/categories
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();//todo
    }

    //add category - localhost:3333/categories
    // Add category
    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);//todo
    }

    //delete category - localhost:3333/categories
    // Delete category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int categoryId) {
        categoryService.deleteCategory(categoryId);//todo
}
