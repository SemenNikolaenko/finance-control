package com.money.demo.controller;

import com.money.demo.model.Category;
import com.money.demo.service.CategoryService;
import com.money.demo.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/app")
public class CategoryController {

    private final CategoryService categoryService;
    private final EntryService EntryService;

    @Autowired
    public CategoryController(CategoryService categoryService, EntryService EntryService) {
        this.categoryService = categoryService;
        this.EntryService = EntryService;
    }

    public String simple() {
        return "test";
    }

    @GetMapping("/cat")
    public String showAllCategories(Model model) {
        List<Category> categories = categoryService.findAllCategory();
        for (Category category : categories) {
            if (category.getFinalAmountExpenses()==0)  category.setFinalAmountExpenses(categoryService.getAllExpensesFromCategoryById(category.getId()));
            else continue;
        }
        model.addAttribute("categories", categories);
        return "category";
    }

    @GetMapping("/edit/{id}")
    public String editCatalog(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "edit-cat";
    }

    @PatchMapping("/edit")
    public String editCatalog(@ModelAttribute(value = "category") Category category) throws Exception {
        categoryService.updateCategories(category);
        return "redirect:/app/cat";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/app/cat";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "add-category";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category) throws Exception {
        categoryService.addCategory(category);
        return "redirect:/app/cat";
    }


}
