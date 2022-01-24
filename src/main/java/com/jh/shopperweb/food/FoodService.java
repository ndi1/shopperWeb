package com.jh.shopperweb.food;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FoodService {
    @Autowired private FoodRepository repo;

    public List<Food> listAll(){
        return (List<Food>) repo.findAll();
    }


    public void save(Food food) {
        repo.save(food);
    }

    public Food get(Integer id) throws FoodNotFoundException {
        Optional<Food> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new FoodNotFoundException("Could not find food with ID "+id);
    }

    public void delete(Integer id) throws FoodNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count ==0){
            throw new FoodNotFoundException("Could not find food with ID "+id);
        }
        repo.deleteById(id);

    }
}
