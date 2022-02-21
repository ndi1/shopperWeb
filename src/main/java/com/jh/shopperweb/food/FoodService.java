package com.jh.shopperweb.food;


import com.jh.shopperweb.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired private FoodRepository repo;

    public List<Food> listAll(){
        return (List<Food>) repo.findAll();
    }

    public List<Food> listUserFoods(Integer userId){
        return repo.findUserFoods(userId);
    }

    public Page<Food> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,10);
        return repo.findAll(pageable);
    }

    public void save(Food food) {
        repo.save(food);
    }

    public Food get(Integer foodId) throws FoodNotFoundException {
        Optional<Food> result = repo.findById(foodId);
        if (result.isPresent()){
            return result.get();
        }
        throw new FoodNotFoundException("Could not find food with ID "+foodId);
    }

    public void delete(Integer foodId) throws FoodNotFoundException {

        repo.deleteById(foodId);

    }

    public List<Food> findByKeyword(String keyword){
        return repo.findByKeyword(keyword);
    }

    public List<Food> findUserFoodByKeyword(String keyword, Integer userId){
        return repo.findUserFoodByKeyword(keyword,userId);
    }

    public List<Food> findUserFoodByDate(String date, Integer userId){
        return repo.findUserFoodsByDate(date, userId);
    }

}
