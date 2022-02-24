package com.jh.shopperweb.recipe;



import com.jh.shopperweb.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Map;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {

    @Query(value = "Select * from recipes r where r.recipe_name like %:keyword% or r.recipe_desc like %:keyword%", nativeQuery = true)
    List<Recipe> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "Select * from recipes r where (r.recipe_name like %:keyword% or r.recipe_desc like %:keyword%) and r.user_id=:userId", nativeQuery = true)
    List<Recipe> findUserRecipeByKeyword(@Param("keyword") String keyword, @Param("userId")Integer userId);

    @Query(value = "Select * from recipes r where r.user_id = ?1",nativeQuery = true)
    List<Recipe> findUserRecipes(Integer userId);

    @Query(value = "Select recipes.* from recipes inner JOIN users_recipes on recipes.recipe_id = users_recipes.recipe_id where users_recipes.date=:date and users_recipes.user_id=:userId", nativeQuery = true)
    List<Recipe> findUserRecipesByDate(@Param("date")String date, @Param("userId")Integer userId);

    @Query(value = "Select coalesce(sum(foods.calories),0) AS calories, coalesce(sum(foods.carbs),0) AS carbs, coalesce(sum(foods.fats),0) AS fats, coalesce(sum(foods.protein),0) As protein, coalesce(sum(foods.price),0) as price, recipes.* From foods inner JOIN recipes_foods on recipes_foods.food_id = foods.food_id Right join recipes on recipes.recipe_id = recipes_foods.recipe_id group by recipe_id",nativeQuery = true)
    List<Map<String,Object>> macrosByRecipe();

    @Query(value = "Select coalesce(sum(foods.calories),0) AS calories, coalesce((foods.carbs),0) AS carbs, coalesce((foods.fats),0) AS fats, coalesce((foods.protein),0) As protein, coalesce(sum(foods.price),0) as price, recipes.* From foods Inner JOIN recipes_foods on recipes_foods.food_id = foods.food_id right join recipes on recipes.recipe_id = recipes_foods.recipe_id group by recipe_id having recipes.recipe_name like %:keyword% or recipes.recipe_desc like %:keyword%",nativeQuery = true)
    List<Map<String,Object>> macrosByRecipeWithKeyword(@Param("keyword")String keyword);

    @Query(value = "Select sum(coalesce(total.calories)) AS recipeCals From (Select ur.*, sum(coalesce(f.calories)) as calories from users_recipes ur inner join recipes r on r.recipe_id=ur.recipe_id inner join recipes_foods rf on rf.recipe_id=r.recipe_id inner join foods f on f.food_id=rf.food_id group by ur.ur_id having ur.date=:date and ur.user_id=:userId) as total",nativeQuery = true)
    Double sumRecipeCalories (@Param("date")String date, @Param("userId")Integer userId);

    @Query(value = "Select sum(coalesce(total.carbs)) AS recipeCarbs From (Select ur.*,sum(coalesce(f.carbs)) as carbs from users_recipes ur inner join recipes r on r.recipe_id=ur.recipe_id inner join recipes_foods rf on rf.recipe_id=r.recipe_id inner join foods f on f.food_id=rf.food_id group by ur.ur_id having ur.date=:date  and ur.user_id=:userId) as total",nativeQuery = true)
    Double sumRecipeCarbs(@Param("date")String date, @Param("userId")Integer userId);

    @Query(value = "Select sum(coalesce(total.fats)) AS recipeFats From (Select ur.*,sum(coalesce(f.fats)) as fats from users_recipes ur inner join recipes r on r.recipe_id=ur.recipe_id inner join recipes_foods rf on rf.recipe_id=r.recipe_id inner join foods f on f.food_id=rf.food_id group by ur.ur_id having ur.date='2022-02-23' and ur.user_id=2) as total",nativeQuery = true)
    Double sumRecipeFats(@Param("date")String date, @Param("userId")Integer userId);

    @Query(value = "Select sum(coalesce(total.protein)) AS recipeProtein From (Select ur.*, sum(coalesce(f.protein)) as protein from users_recipes ur inner join recipes r on r.recipe_id=ur.recipe_id inner join recipes_foods rf on rf.recipe_id=r.recipe_id inner join foods f on f.food_id=rf.food_id group by ur.ur_id having ur.date='2022-02-23' and ur.user_id=2) as total",nativeQuery = true)
    Double sumRecipeProtein(@Param("date")String date, @Param("userId")Integer userId);

    @Query(value = "Select sum(coalesce(total.price)) AS recipePrice From (Select ur.*, sum(coalesce(f.price)) as price from users_recipes ur inner join recipes r on r.recipe_id=ur.recipe_id inner join recipes_foods rf on rf.recipe_id=r.recipe_id inner join foods f on f.food_id=rf.food_id group by ur.ur_id having ur.date='2022-02-23' and ur.user_id=2) as total",nativeQuery = true)
    Double sumRecipePrice(@Param("date")String date, @Param("userId")Integer userId);

}
