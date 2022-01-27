package com.jh.shopperweb.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired private ItemRepository repo;

    public List<Item> listAll() {
        return (List<Item>) repo.findAll();
    }


    public void save(Item item) {
        repo.save(item);
    }

    public Item get(Integer id) throws ItemNotFoundException {
        Optional<Item> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ItemNotFoundException("Could not find Item with ID " + id);
    }

    public void delete(Integer id) throws ItemNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new ItemNotFoundException("Could not find Item with ID " + id);
        }
        repo.deleteById(id);

    }
}