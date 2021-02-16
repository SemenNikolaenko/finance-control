package com.money.demo.repository;

import com.money.demo.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("select i from Category i where i.id=?1")
    @EntityGraph(value = "getCategoryWithSubcategories")
    public Category findCategoryByIdWithSubcategories(Long id);

    @Query("select SUM(e.amountOfMoney) from Entry e LEFT JOIN Subcategory s ON e.subcategory.id = s.id where s.category.id=?1")
    public int getAllExpensesInCategoryById(Long id);


}
