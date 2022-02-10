package com.jh.shopperweb.food;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food,Integer> {

    @Query(value = "Select foods.* FROM foods inner JOIN recipes_foods on recipes_foods.food_id = foods.food_id inner join recipes on recipes.recipe_id = recipes_foods.recipe_id WHERE recipes.recipe_id=?1", nativeQuery=true)
    List<Food> findFoodsByRecipeId(Integer id);

    @Query(value = "Select * from foods f where f.food_name like %:keyword% or f.food_desc like %:keyword%", nativeQuery = true)
    List<Food> findByKeyword(@Param("keyword") String keyword);

}
