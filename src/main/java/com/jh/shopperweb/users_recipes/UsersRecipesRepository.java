package com.jh.shopperweb.users_recipes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

//Repository which contains all queries related to a user's recipes
public interface UsersRecipesRepository extends JpaRepository<UsersRecipes,Integer> {

    @Modifying
    @Transactional
    @Query(value = "Delete FROM users_recipes where date=:date and user_id=:userId and recipe_id=:recipeId LIMIT 1",nativeQuery = true)
    void deleteRecipesFromDay (@Param("date")String date, @Param("userId")Integer userId, @Param("recipeId")Integer recipeId);

}
