package com.jh.shopperweb.food;

import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food,Integer> {

    public Long countById(Integer id);

}
