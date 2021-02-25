package com.money.demo.service.impl;

import com.money.demo.model.Category;
import com.money.demo.model.Subcategory;
import com.money.demo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private  CategoryRepository categoryRepository;

    @InjectMocks
    private  CategoryServiceImpl categoryService;

    static Category testCategory;

    @BeforeAll
    static void initiateTestCategory() {
        testCategory = new Category();
        testCategory.setFinalAmountExpenses(1200);
        testCategory.setName("House");
        testCategory.setDefinition("House expenses");
        testCategory.setId(1L);
        Subcategory subcategory1 = new Subcategory();
        Subcategory subcategory2 = new Subcategory();
        Subcategory subcategory3 = new Subcategory();
        testCategory.getSubcategoryList().add(subcategory1);
        testCategory.getSubcategoryList().add(subcategory2);
        testCategory.getSubcategoryList().add(subcategory3);


    }

    @Test
    @DisplayName("categoryByIdTest")
    void getCategoryByIdTest() {
        when(categoryRepository.getOne(anyLong())).thenReturn(testCategory);
        assertNotNull(categoryService.getCategoryById(1l));
        assertEquals(categoryService.getCategoryById(4l), testCategory);
    }

    @Test
    @DisplayName("getCategoryWithAllSubcategories")
    void getCategoryWithSubcategoriesById() {
        when(categoryRepository.findCategoryByIdWithSubcategories(anyLong())).thenReturn(testCategory);

        assertNotNull(categoryService.getCategoryWithSubcategoriesById(23L).getSubcategoryList());
    }

    @Test
    void updateCategories() {
        when(categoryRepository.findCategoryByIdWithSubcategories(anyLong())).thenReturn(testCategory);

        Category categoryWithoutName = testCategory;
        categoryWithoutName.setName("");
        categoryWithoutName.setDefinition("definition");

        Category differentCategory = new Category();
        differentCategory.setDefinition("Car");
        differentCategory.setName("car");
        differentCategory.setId(1L);

        assertFalse(categoryService.updateCategories(categoryWithoutName));
        assertTrue(categoryService.updateCategories(differentCategory));
    }

    @Test
    void addCategory() {
        when(categoryRepository.save(testCategory)).thenReturn(testCategory);

        assertTrue(categoryService.addCategory(testCategory));
    }

    @Test
    void deleteCategoryById() {
        when(categoryRepository.existsById(1L)).thenReturn(false);
        when(categoryRepository.existsById(2L)).thenReturn(true);

        assertTrue(categoryService.deleteCategoryById(1l));
        assertFalse(categoryService.deleteCategoryById(2l));
    }

    @Test
    void findAllCategory() {
        when(categoryRepository.findAll()).thenReturn(new ArrayList<Category>());
        assertEquals(categoryService.findAllCategory().size(), 0);
    }
}