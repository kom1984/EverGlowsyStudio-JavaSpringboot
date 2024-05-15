package com.everglowsy.projectfinal.Service;

import com.everglowsy.projectfinal.model.CategoryModel;
import com.everglowsy.projectfinal.repository.CategoryRepository;
import com.everglowsy.projectfinal.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testSaveCategory() {
        // Create a category object to save
        CategoryModel categoryToSave = new CategoryModel(null, "Lissage Brésilien");
        // Mocking behavior of the repository
        when(categoryRepository.save(categoryToSave)).thenReturn(new CategoryModel(1L, "Lissage Brésilien"));
        // Calling the method under test
        categoryService.saveCategory(categoryToSave);
        // Verifying that the save method was called with the correct category
        verify(categoryRepository, times(1)).save(categoryToSave);
    }
    @Test
    void testGetAllCategory() {
       when(categoryRepository.findAll()).thenReturn(Arrays.asList(
                new CategoryModel(1L, "Pedicure"),
                new CategoryModel(2L, "Vérnis Pérmanant")
        ));
        List<CategoryModel> categories = categoryService.getAllCategory();
        assertEquals(2, categories.size());
    }

    @Test
    void testGetCategoryById() {
        // Mocking categoryRepository behavior
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(new CategoryModel(1L, "Pedicure")));

        CategoryModel category = categoryService.getCategoryById(1L);

        // Verifying the result
        assertEquals("Pedicure", category.getName_category());
    }
    @Test
    void testDeleteCategory() {
        // Calling the method under test
        categoryService.deleteCategory(1L);

        // Verifying that the delete method was called with the correct ID
        verify(categoryRepository, times(1)).deleteById(1L);
    }

}
