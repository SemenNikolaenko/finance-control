package com.money.demo.service.impl;

import com.money.demo.model.Category;
import com.money.demo.model.Entry;
import com.money.demo.model.Subcategory;
import com.money.demo.repository.SubcategoryRepository;
import org.junit.jupiter.api.BeforeAll;
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
class SubcategoryServiceImplTest {

    @Mock
    private SubcategoryRepository subcategoryRepository;
    @InjectMocks
    private SubcategoryServiceImpl subcategoryService;

    private static Subcategory testSubcategory;

    @BeforeAll
    public static void initiateSubcategory(){
        testSubcategory=new Subcategory();
        testSubcategory.setId(1L);
        testSubcategory.setName("Rent");
        Category category = new Category("Home","Home");
        category.setId(1L);
        Entry entry1 = new Entry();
        Entry entry2 = new Entry();
        testSubcategory.setCategory(category);
        testSubcategory.getListOfEntry().add(entry1);
        testSubcategory.getListOfEntry().add(entry2);
    }

    @Test
    void getSubcategoryWithEntriesById() {
        when(subcategoryRepository.findSubcategoriesWithEntriesById(anyLong())).thenReturn(testSubcategory);

        assertEquals(testSubcategory,subcategoryService.getSubcategoryWithEntriesById(1L));
    }

    @Test
    void getSubcategory() {
        when(subcategoryRepository.getOne(1L)).thenReturn(testSubcategory);
        assertNotNull(subcategoryService.getSubcategory(1L));
    }

    @Test
    void updateSubcategory() {
        when(subcategoryRepository.getOne(1L)).thenReturn(testSubcategory);

        assertFalse(subcategoryService.updateSubcategory(testSubcategory));

    }

    @Test
    void addSubcategory() {
        when(subcategoryRepository.save(testSubcategory)).thenReturn(testSubcategory);

        assertTrue(subcategoryService.addSubcategory(testSubcategory,1l));
    }

    @Test
    void deleteSubcategoryById() {
        when(subcategoryRepository.existsById(1L)).thenReturn(false);
        when(subcategoryRepository.existsById(2L)).thenReturn(true);
        assertTrue(subcategoryService.deleteSubcategoryById(1L));
        assertFalse(subcategoryService.deleteSubcategoryById(2L));
    }

    @Test
    void getAllSubcategoryByCategoryId() {
        when(subcategoryRepository.findSubcategoriesByCategoryId(1L)).thenReturn(new ArrayList<Subcategory>());
        assertEquals(0,subcategoryService.getAllSubcategoryByCategoryId(1L).size());
    }
}