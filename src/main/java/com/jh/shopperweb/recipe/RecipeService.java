package com.jh.shopperweb.recipe;


import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository repo;
    @Autowired
    private FoodRepository foodRepo;

    public List<Recipe> listAll(){
        return (List<Recipe>) repo.findAll();
    }

    public void save(Recipe recipe) {
        repo.save(recipe);
    }

    public Recipe get(Integer recipeId) throws RecipeNotFoundException {
        Optional<Recipe> result = repo.findById(recipeId);
        if (result.isPresent()){
            return result.get();
        }
        throw new RecipeNotFoundException("Could not find recipe with ID "+recipeId);
    }

    public void delete(Integer recipeId) throws RecipeNotFoundException {
        repo.deleteById(recipeId);

    }

    public List<Food> findAllFoods(Integer recipeId){
        List<Food> foods = foodRepo.findFoodsByRecipeId(recipeId);
        return foods;
    }

    public List<Recipe> findByKeyword(String keyword){
        return repo.findByKeyword(keyword);
    }

    public List<Recipe> findUserRecipeByKeyword(String keyword, Integer userId){
        return repo.findUserRecipeByKeyword(keyword,userId);
    }

    public List<Recipe> listUserRecipes(Integer userId){
        return repo.findUserRecipes(userId);
    }


    public List<Recipe> findUserRecipesByDate(String date, Integer userId){
        return repo.findUserRecipesByDate(date,userId);
    }

    public List<Map<String,Object>> macrosByRecipe(){
        return repo.macrosByRecipe();
    }

    public List<Map<String,Object>> macrosByRecipeWithKeyword(String keyword){
        return repo.macrosByRecipeWithKeyword(keyword);
    }

    public Double sumRecipeCalories(String date, Integer userId){
        return repo.sumRecipeCalories(date, userId);
    }

    public Double sumRecipeCarbs(String date, Integer userId){
        return repo.sumRecipeCarbs(date,userId);
    }

    public Double sumRecipeFats(String date, Integer userId){
        return repo.sumRecipeFats(date,userId);
    }

    public Double sumRecipeProtein(String date, Integer userId){
        return repo.sumRecipeProtein(date,userId);
    }

    public Double sumRecipePrice(String date, Integer userId){
        return repo.sumRecipePrice(date,userId);
    }
}
