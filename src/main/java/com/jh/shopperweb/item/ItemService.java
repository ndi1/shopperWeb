package com.jh.shopperweb.item;


import com.jh.shopperweb.food.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Service layer to implement the item repository queries
@Service
public class ItemService {
    @Autowired private ItemRepository repo;

    public List<Item> listAll() {
        return (List<Item>) repo.findAll();
    }

    public List<Item> listUserItems(Integer userId){
        return repo.findUserItems(userId);
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

    public List<Item> findByKeyword(String keyword){
        return repo.findByKeyword(keyword);
    }

    public List<Item> findUserItemByKeyword(String keyword, Integer itemId){
        return repo.findUserItemByKeyword(keyword,itemId);
    }

    public List<Item> findUserFoodByDate(String date, Integer userId){
        return repo.findUserItemsByDate(date, userId);
    }


}