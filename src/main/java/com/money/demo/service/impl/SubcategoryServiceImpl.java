package com.money.demo.service.impl;

import com.money.demo.model.Category;
import com.money.demo.model.Entry;
import com.money.demo.model.Subcategory;
import com.money.demo.repository.SubcategoryRepository;
import com.money.demo.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }


    public Subcategory getSubcategoryWithEntriesById(Long id){
        return subcategoryRepository.findSubcategoriesWithEntriesById(id);
    }

    public Subcategory getSubcategory(Long id){
        return subcategoryRepository.getOne(id);
    }

    @Transactional(readOnly = false)
    public boolean updateSubcategory(Subcategory subcategory) throws Exception {
        if (subcategory==null) throw new NullPointerException();
        Subcategory subcategoryFromBd = subcategoryRepository.getOne(subcategory.getId());
        if (!subcategoryFromBd.equals(subcategory)){
            subcategoryFromBd.setName(subcategory.getName());
            return true;
        }
        else return false;
    }
    @Transactional(readOnly = false)
    public boolean addSubcategory(Subcategory subcategory,  Long categoryId){
        Category category = subcategoryRepository.findCategoryByID(categoryId);
        subcategory.setCategory(category);
        if (!subcategoryRepository.save(subcategory).equals(null)) return true;
        else return false;
    }
    @Transactional(readOnly = false)
    public boolean deleteSubcategoryById(Long id){
        subcategoryRepository.deleteById(id);
        if (!subcategoryRepository.existsById(id)) return true;
        else return false;
    }
    public List<Subcategory> getAllSubcategoryByCategoryId(Long id){
        return subcategoryRepository.findSubcategoriesByCategoryId(id);
    }

    public int getAllExpensesFromSubcategoryById(Long id){
        Subcategory category = subcategoryRepository.findSubcategoriesWithEntriesById(id);
        List<Entry> entryList = category.getListOfEntry();
        if (entryList.isEmpty()) return 0;
        else return subcategoryRepository.findAllExpensesThisSubcategoryById(id);
    }
}
