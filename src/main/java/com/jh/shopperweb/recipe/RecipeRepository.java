package com.jh.shopperweb.recipe;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {

    @Query(value = "Select * from recipes r where r.recipe_name like %:keyword% or r.recipe_desc like %:keyword%", nativeQuery = true)
    List<Recipe> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "Select * from recipes r where (r.recipe_name like %:keyword% or r.recipe_desc like %:keyword%) and r.user_id=:userId", nativeQuery = true)
    List<Recipe> findUserRecipeByKeyword(@Param("keyword") String keyword, @Param("userId")Integer userId);

    @Query(value = "Select * from recipes r where r.user_id = ?1",nativeQuery = true)
    List<Recipe> findUserRecipes(Integer userId);


}
