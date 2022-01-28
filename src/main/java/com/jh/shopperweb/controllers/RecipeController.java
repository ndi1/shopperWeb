package com.jh.shopperweb.controllers;


import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodNotFoundException;
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

import java.util.List;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService service;


    @GetMapping("/myRecipes")
    public String showRecipeList(Model model){

        List<Recipe> listRecipe = service.listAll();
        model.addAttribute("listRecipes",listRecipe);
        return "myRecipes";
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

    @GetMapping("/myRecipes/foods/{recipeId}")
    public String showFoods(@PathVariable("recipeId") Integer recipeId, Model model){
        List<Food> listFoods = service.findAllFoods(recipeId);
    model.addAttribute("listFoods",listFoods);
    return "recipeFoods";
    }

    @PostMapping("/myRecipes/save")
    public String saveRecipes(Recipe recipe){
        service.save(recipe);
        return "redirect:/myRecipes";
    }




}
