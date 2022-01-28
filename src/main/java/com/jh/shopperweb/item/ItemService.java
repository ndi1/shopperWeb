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

    public Item get(Integer itemId) throws ItemNotFoundException {
        Optional<Item> result = repo.findById(itemId);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ItemNotFoundException("Could not find Item with ID " + itemId);
    }

    public void delete(Integer itemId) throws ItemNotFoundException {

        repo.deleteById(itemId);

    }
}