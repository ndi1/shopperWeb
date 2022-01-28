package com.jh.shopperweb.recipe;


import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
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


}
