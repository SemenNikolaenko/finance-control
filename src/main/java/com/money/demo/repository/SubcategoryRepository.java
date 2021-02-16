package com.money.demo.repository;

import com.money.demo.model.Category;
import com.money.demo.model.Subcategory;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    @EntityGraph(value = "getSubcategoryWithEntry")
    @Query("select i from Subcategory i where i.id=?1")
    public Subcategory findSubcategoriesWithEntriesById(Long id);

    @Query("select sum(e.amountOfMoney) from Entry e left join Subcategory s on e.subcategory.id=s.id where s.id=?1")
    public int findAllExpensesThisSubcategoryById(Long id);

    public List<Subcategory> findSubcategoriesByCategoryId(Long id);

    @Query("select i from Category i where i.id=?1")
    public Category findCategoryByID(Long id);
}
