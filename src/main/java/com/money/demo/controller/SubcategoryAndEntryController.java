package com.money.demo.controller;

import com.money.demo.model.Category;
import com.money.demo.model.Entry;
import com.money.demo.model.Subcategory;
import com.money.demo.service.EntryService;
import com.money.demo.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sub")
public class SubcategoryAndEntryController {
    private final SubcategoryService subcategoryService;
    private final EntryService entryService;
    private Long categoryId = 0l;
    private List<Entry> entryList = new ArrayList<>();

    @Autowired
    public SubcategoryAndEntryController(SubcategoryService subcategoryService, EntryService entryService) {
        this.subcategoryService = subcategoryService;
        this.entryService = entryService;
    }


    @GetMapping("/{id}")
    public String showAllSubcategories(@PathVariable("id") Long id, Model model) {
        categoryId = id;
        List<Subcategory> subcategoryList = subcategoryService.getAllSubcategoryByCategoryId(id);
        for (Subcategory subcategory : subcategoryList) {
            if (subcategory.getTotalExpenses() == 0)
                subcategory.setTotalExpenses(subcategoryService.getAllExpensesFromSubcategoryById(subcategory.getId()));
            else continue;
        }
        if (entryList.size()!=0) model.addAttribute("entryList", entryList) ;
        model.addAttribute("subcategories", subcategoryList);
        return "subcategory";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSubcategory(@PathVariable("id") Long id) {
        subcategoryService.deleteSubcategoryById(id);
        return "redirect:/sub/" + categoryId;
    }

    @GetMapping("/add")
    public String addSubcategory(Model model) {
        Subcategory subcategory = new Subcategory();
        model.addAttribute("subcategory", subcategory);
        return "add-subcategory";
    }

    @PostMapping("/add")
    public String addSubcategory(@ModelAttribute("subcategory") Subcategory subcategory) {
        subcategoryService.addSubcategory(subcategory, categoryId);
        return "redirect:/sub/" + categoryId;
    }


    @GetMapping("/edit/{id}")
    public String editSubcategory(@PathVariable("id") Long id, Model model){
        Subcategory subcategory = subcategoryService.getSubcategory(id);
        model.addAttribute("subcategory", subcategory);
        return "edit-sub";
    }
    @PatchMapping("/edit")
    public String editSubcategory(@ModelAttribute("subcategory") Subcategory subcategory) throws Exception {
        subcategoryService.updateSubcategory(subcategory);
        return "redirect:/sub/" + categoryId;
    }
    @GetMapping("/show/{id}")
    public String showExpensesEntry(@PathVariable("id")Long id, Model model){
        entryList = entryService.getEntriesBySubcategoryId(id);
        return "redirect:/sub/" + categoryId;
    }

}
