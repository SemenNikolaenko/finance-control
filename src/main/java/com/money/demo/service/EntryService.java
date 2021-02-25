package com.money.demo.service;

import com.money.demo.model.Entry;

import java.util.List;

public interface EntryService {
    public boolean updateEntry(Entry entry);
    public boolean deleteEntryByID(Long id);
    public List<Entry> getEntriesBySubcategoryId(Long subcategoryId);
}
