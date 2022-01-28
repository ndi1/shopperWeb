package com.jh.shopperweb.recipe;

import com.jh.shopperweb.food.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe,Integer> {



}
