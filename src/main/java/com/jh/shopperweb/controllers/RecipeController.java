package com.jh.shopperweb.controllers;


import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodNotFoundException;
import com.jh.shopperweb.food.FoodService;
import com.jh.shopperweb.recipe.Recipe;
import com.jh.shopperweb.recipe.RecipeNotFoundException;
import com.jh.shopperweb.recipe.RecipeService;
import com.jh.shopperweb.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService service;

    @Autowired
    private FoodService foodService;


    @GetMapping("/myRecipes")
    public String showRecipeList(Model model, String keyword){

        if (keyword != null){
            model.addAttribute("listRecipes",service.findByKeyword(keyword));
        }
        else{
            List<Recipe> listRecipes = service.listAll();
            model.addAttribute("listRecipes",listRecipes);
        }

        return "myRecipes";
    }

    @GetMapping("/myRecipes/addFoods/add/{recipeId}/{foodId}")
    public String addFoodToRecipe(@PathVariable("recipeId")Integer recipeId, @PathVariable("foodId") Integer foodId) throws FoodNotFoundException {
        try {
            Recipe recipe = service.get(recipeId);
            Food food = foodService.get(foodId);
            Set<Food> recipeFoods = new HashSet<Food>();
            recipeFoods = recipe.getFoods();
            recipeFoods.add(food);
            recipe.setFoods(recipeFoods);
            service.save(recipe);
            return "redirect:/myRecipes/addFoods/"+recipeId;
        } catch (RecipeNotFoundException e) {
            e.printStackTrace();
            return "myRecipes";
        }


    }



    @GetMapping("/myRecipes/delete/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") Integer recipeId, RedirectAttributes ra){
        try {
            service.delete(recipeId);
            ra.addFlashAttribute("message", "The Recipe ID " + recipeId + " has been deleted.");

        } catch (RecipeNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/myRecipes";
    }

    @GetMapping("/myRecipes/new")
    public String showNewRecipeForm(Model model){


        model.addAttribute("recipe",new Recipe());
        model.addAttribute("pageTitle","Add New Recipe");
        return "addRecipes";
    }


    @PostMapping("/myRecipes/save")
    public String saveRecipes(Recipe recipe){

        service.save(recipe);

        return "redirect:/myRecipes/addFoods/"+recipe.getRecipeId();
    }

    @GetMapping("/myRecipes/addFoods/{recipeId}")
    public String showAddFoodsToRecipe(@PathVariable("recipeId")Integer recipeId, Model model, String keyword){
        try {
            Recipe recipe = service.get(recipeId);

            if (keyword != null){
                model.addAttribute("listFoods",foodService.findByKeyword(keyword));
            }
            else{
                List<Food> listFoods = foodService.listAll();
                model.addAttribute("listFoods",listFoods);
            }

            model.addAttribute("recipe", recipe);
            model.addAttribute("pageTitle", "Edit Recipe (ID: " + recipeId + ")");
            return "recipeFoods";
        } catch (RecipeNotFoundException e) {
            return "redirect:/myRecipes";

        }


    }


    @GetMapping("/myRecipes/edit/{recipeId}")
    public String showRecipeEditForm(@PathVariable("recipeId") Integer recipeId, Model model, RedirectAttributes ra) {
        try {
            Recipe recipe = service.get(recipeId);
            List<Food> listFoods = service.findAllFoods(recipeId);
            model.addAttribute("listFoods", listFoods);
            model.addAttribute("recipe", recipe);
            model.addAttribute("pageTitle", "Edit Recipe (ID: " + recipeId + ")");
            return "editRecipe";
        } catch (RecipeNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/myRecipes";

        }
    }


    @GetMapping("/myRecipes/addFoods/delete/{recipeId}/{foodId}")
    public String deleteFoodFromRecipe(@PathVariable("recipeId")Integer recipeId, @PathVariable("foodId") Integer foodId) throws FoodNotFoundException {
        try {
            Recipe recipe = service.get(recipeId);
            Food food = foodService.get(foodId);
            Set<Food> recipeFoods = recipe.getFoods();
            recipeFoods.remove(food);
            recipe.setFoods(recipeFoods);
            service.save(recipe);
            return "redirect:/myRecipes/edit/" + recipeId;
        } catch (RecipeNotFoundException e) {
            e.printStackTrace();
            return "myRecipes";
        }
    }

/*    @PostMapping("/myRecipes/edit/save/{recipeId}")
    public String saveRecipesAndFoods(@PathVariable("recipeId")Integer recipeId) throws RecipeNotFoundException {

        Recipe recipe = service.get(recipeId);
        Set<Food> recipeFoods = recipe.getFoods();
        recipe.setFoods(recipeFoods);

        service.save(recipe);

        return "addRecipes";
    }*/


}
