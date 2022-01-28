package com.jh.shopperweb.food;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired private FoodRepository repo;

    public List<Food> listAll(){
        return (List<Food>) repo.findAll();
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
}
