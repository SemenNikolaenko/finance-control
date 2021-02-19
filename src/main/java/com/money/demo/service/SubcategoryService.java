package com.money.demo.service;

import com.money.demo.model.Subcategory;

import java.util.List;

public interface SubcategoryService {
    public Subcategory getSubcategory(Long id);
    public boolean updateSubcategory(Subcategory subcategory) throws Exception;
    public boolean addSubcategory(Subcategory subcategory,  Long categoryId);
    public boolean deleteSubcategoryById(Long id);
    public List<Subcategory> getAllSubcategoryByCategoryId(Long id);
    public int getAllExpensesFromSubcategoryById(Long id);
}
