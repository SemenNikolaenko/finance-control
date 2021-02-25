package com.money.demo.service;

import com.money.demo.model.Category;

import java.util.List;

public interface CategoryService {
    public Category getCategoryById(Long id);
    public boolean updateCategories(Category category);
    public boolean addCategory(Category category);
    public boolean deleteCategoryById(Long id);
    public List<Category> findAllCategory();
    public int getAllExpensesFromCategoryById(Long id);
    public Category getCategoryWithSubcategoriesById(Long id);
}
