package com.jh.shopperweb.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Integer> {

    @Query(value = "Select foods.* FROM foods inner JOIN recipes_foods on recipes_foods.food_id = foods.food_id inner join recipes on recipes.recipe_id = recipes_foods.recipe_id WHERE recipes.recipe_id=?1", nativeQuery=true)
    List<Food> findFoodsByRecipeId(Integer id);

    @Query(value = "Select * from foods f where f.food_name like %:keyword% or f.food_desc like %:keyword%", nativeQuery = true)
    List<Food> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "Select * from foods f where (f.food_name like %:keyword% or f.food_desc like %:keyword%) and f.user_id=:userId", nativeQuery = true)
    List<Food> findUserFoodByKeyword(@Param("keyword") String keyword, @Param("userId")Integer userId);

    @Query(value = "Select * from foods f where f.user_id = ?1",nativeQuery = true)
    List<Food> findUserFoods(Integer userId);

    @Query(value = "Select foods.* from foods inner JOIN users_foods on foods.food_id = users_foods.food_id where users_foods.date=:date and users_foods.user_id=:userId", nativeQuery = true)
    List<Food> findUserFoodsByDate(@Param("date")String date, @Param("userId")Integer userId);

}
