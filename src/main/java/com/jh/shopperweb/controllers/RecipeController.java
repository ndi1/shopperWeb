package com.jh.shopperweb.controllers;


import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodNotFoundException;
import com.jh.shopperweb.food.FoodService;
import com.jh.shopperweb.recipe.Recipe;
import com.jh.shopperweb.recipe.RecipeNotFoundException;
import com.jh.shopperweb.recipe.RecipeService;
import com.jh.shopperweb.user.MyUserDetailsService;
import com.jh.shopperweb.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//Controller that handles the viewing, creation, and editing/deleting of a recipe
@Controller
public class RecipeController {
    @Autowired
    private RecipeService service;

    @Autowired
    private FoodService foodService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    //Mapping to so all user created recipes
    @GetMapping("/myRecipes")
    public String showRecipeList(Model model, String keyword){

        if (keyword != null){
            List<Map<String,Object>> listRecipes = service.macrosByRecipeWithKeyword(keyword);
            model.addAttribute("listRecipes",listRecipes);
        }
        else{
            List<Map<String,Object>> listRecipes = service.macrosByRecipe();
            model.addAttribute("listRecipes",listRecipes);
        }

        return "myRecipes";
    }

    //Mapping to add a food to a recipe
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

    //Mapping to delete a user's recipe
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

    //Mapping to create a new user recipe
    @GetMapping("/myRecipes/new")
    public String showNewRecipeForm(Model model){


        model.addAttribute("recipe",new Recipe());
        model.addAttribute("pageTitle","Add New Recipe");
        return "addRecipes";
    }

    //Mapping to save an edited user recipe
    @PostMapping("myRecipes/save/{recipeId}")
    public String saveEditRecipes(@PathVariable("recipeId")Integer recipeId, @ModelAttribute("recipe") Recipe recipe) throws RecipeNotFoundException {

       Recipe recipes = service.get(recipeId);
       Set<Food> foods = recipes.getFoods();
       recipes.setFoods(foods);
       String updatedName = recipe.getRecipeName();
       String updatedDesc = recipe.getRecipeDesc();
       recipes.setRecipeName(updatedName);
       recipes.setRecipeDesc(updatedDesc);
       service.save(recipes);

        return "redirect:/myRecipes";
    }

    //Mapping to save a user created recipe
    @PostMapping("/myRecipes/save")
    public String saveRecipes(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);

        if (bindingResult.hasErrors()){
            return "addRecipes";
        }
        recipe.setUserRecipe(user);
        Set<Food> recipeFoods = recipe.getFoods();
        recipe.setFoods(recipeFoods);
        service.save(recipe);

        return "redirect:/myRecipes/addFoods/"+recipe.getRecipeId();
    }

    //Mapping to add a food to a recipe
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


    //Mapping to saved an edited recipe
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


    //Mapping to delete a food from a recipe
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

    //Mapping to add a recipe to the daily food log
    @GetMapping("/myRecipes/addToDiary")
    public String addRecipeToDiary(Model model, String keyword){

        if (keyword != null){
            model.addAttribute("listRecipes",service.findByKeyword(keyword));
        }
        else{
            List<Recipe> listRecipes = service.listAll();
            model.addAttribute("listRecipes",listRecipes);
        }

        return "addRecipeToDay";
    }


    //Mapping to show a user's personal recipe
    @GetMapping("/myRecipes/personal")
    public String showMyRecipeList(Model model, String keyword){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);
        Integer curId = user.getUserId();

        if (keyword != null){
            model.addAttribute("listRecipes",service.findUserRecipeByKeyword(keyword,curId));
        }
        else{
            List<Recipe> listRecipes = service.listUserRecipes(curId);
            model.addAttribute("listRecipes",listRecipes);
        }

        return "myRecipesPersonal";
    }


}
