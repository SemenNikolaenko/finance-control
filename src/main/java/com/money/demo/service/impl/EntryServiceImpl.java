package com.money.demo.service.impl;

import com.money.demo.model.Entry;
import com.money.demo.repository.EntryRepository;
import com.money.demo.service.EntryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;

    public EntryServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry getEntryById(Long id) {
        return entryRepository.getOne(id);
    }

    public List<Entry> getAllEntry() {
        return entryRepository.findAll();
    }

    @Transactional(readOnly = false)
    public boolean updateEntry(Entry entry) {
        Entry entryFromBd = entryRepository.getOne(entry.getId());
        if (entryFromBd.equals(entry)) return false;
        else {
            entryFromBd.setAmountOfMoney(entry.getAmountOfMoney());
            entryFromBd.setText(entry.getText());
            return true;
        }
    }

    @Transactional(readOnly = false)
    public boolean deleteEntryByID(Long id) {
        entryRepository.deleteById(id);
        if (entryRepository.existsById(id)) return false;
        else return true;
    }

    public List<Entry> getEntriesBySubcategoryId(Long subcategoryId){
        return entryRepository.findEntriesBySubcategoryId(subcategoryId);
    }
}
