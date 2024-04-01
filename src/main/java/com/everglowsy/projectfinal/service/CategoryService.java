package com.everglowsy.projectfinal.service;

import com.everglowsy.projectfinal.model.CategoryModel;
import com.everglowsy.projectfinal.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getAllCategory()
    {

        return categoryRepository.findAll();
    }
    public CategoryModel getCategoryById(Long id)
    {

        return categoryRepository.findById(id).get();
    }
    public void saveCategory(CategoryModel savecategory)
    {
        categoryRepository.save(savecategory);
    }
    public void deleteCategory(Long id)
    {
        categoryRepository.deleteById(id);
    }
}

