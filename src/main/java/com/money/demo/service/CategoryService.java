package com.money.demo.service;

import com.money.demo.model.Category;
import com.money.demo.model.Subcategory;
import com.money.demo.repository.CategoryRepository;
import com.money.demo.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(Long id){
        return categoryRepository.getOne(id);
    }
    public Category getCategoryWithSubcategoriesById(Long id){
        return categoryRepository.findCategoryByIdWithSubcategories(id);
    }
    @Transactional(readOnly = false)
    public boolean updateCategories(Category category) throws Exception {
        if (category==null) throw new NullPointerException();
       Category categoryFromBd = categoryRepository.findCategoryByIdWithSubcategories(category.getId());
       if (!categoryFromBd.equals(category)){
           categoryFromBd.setDefinition(category.getDefinition());
           categoryFromBd.setName(category.getName());
           return true;
       }
       else return false;
    }
    @Transactional(readOnly = false)
    public boolean addCategory(Category category){
      if (!categoryRepository.save(category).equals(null)) return true;
      else return false;
    }
    @Transactional(readOnly = false)
    public boolean deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
        if (!categoryRepository.existsById(id)) return true;
        else return false;
    }
    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }

    public int getAllExpensesFromCategoryById(Long id){
        Category category = categoryRepository.findCategoryByIdWithSubcategories(id);
        List<Subcategory> subcategories = category.getSubcategoryList();
        if (subcategories.isEmpty() || subcategories.get(0).getListOfEntry().size()==0) return 0;
        else return categoryRepository.getAllExpensesInCategoryById(id);
    }


}
