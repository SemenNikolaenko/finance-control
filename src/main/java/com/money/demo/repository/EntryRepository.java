package com.money.demo.repository;

import com.money.demo.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry,Long> {

    @Query("select i from Entry i where i.subcategory.id=?1")
    public List<Entry> findEntriesBySubcategoryId(Long subcategoryID);
}
