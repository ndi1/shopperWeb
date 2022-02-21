package com.jh.shopperweb.controllers;


import com.jh.shopperweb.calendar.DiaryFormData;
import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodNotFoundException;
import com.jh.shopperweb.food.FoodService;
import com.jh.shopperweb.recipe.Recipe;
import com.jh.shopperweb.recipe.RecipeNotFoundException;
import com.jh.shopperweb.recipe.RecipeService;
import com.jh.shopperweb.user.MyUserDetailsService;
import com.jh.shopperweb.user.User;
import com.jh.shopperweb.users_foods.UsersFoods;
import com.jh.shopperweb.users_foods.UsersFoodsService;
import com.jh.shopperweb.users_recipes.UsersRecipes;
import com.jh.shopperweb.users_recipes.UsersRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Date;
import java.util.List;



@Controller
public class CalendarController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UsersRecipesService usersRecipesService;

    @Autowired
    private UsersFoodsService usersFoodsService;

    @GetMapping("/myCalendar")
    public String showCalendar(Model model){

        Date selectedDate = new Date();
        model.addAttribute("selectedDate", selectedDate);
        model.addAttribute("data",new DiaryFormData());




        return "calendar";
    }


    @PostMapping("/myCalendar/load")
    public String foodDiaryDate(DiaryFormData data){
        return "redirect:/myCalendar/"+data.getDiaryDate();
    }

    @GetMapping("/myCalendar/{foodDate}")
    public String showFoodDate(@PathVariable("foodDate") String foodDate, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);
        Integer curId = user.getUserId();


        model.addAttribute("selectedDate",foodDate);
        model.addAttribute("data", new DiaryFormData());
        DiaryFormData diaryFormData = new DiaryFormData();
        diaryFormData.setDiaryDate(foodDate);



     List<Food> foodsByDate = foodService.findUserFoodByDate(foodDate,curId);
        model.addAttribute("foodsByDate", foodsByDate);


    List<Recipe> recipesByDate = recipeService.findUserRecipesByDate(foodDate,curId);
    model.addAttribute("recipesByDate",recipesByDate);



        return "foodDiary";
    }


    @GetMapping("/myCalendar/{foodDate}/addRecipe")
    public String showAddRecipeToDay(@PathVariable("foodDate") String date, Model model, String keyword){


        model.addAttribute("selectedDate",date);

        if (keyword != null){
            model.addAttribute("listRecipes",recipeService.findByKeyword(keyword));
        }
        else{
            List<Recipe> listRecipes = recipeService.listAll();
            model.addAttribute("listRecipes",listRecipes);
        }


        return "addRecipeToDay";

    }


    @GetMapping("/myCalendar/{foodDate}/addFood")
    public String showAddFoodToDay(@PathVariable("foodDate") String date, Model model, String keyword){


        model.addAttribute("selectedDate",date);

        if (keyword != null){
            model.addAttribute("listFoods",foodService.findByKeyword(keyword));
        }
        else{
            List<Food> listFoods = foodService.listAll();
            model.addAttribute("listFoods",listFoods);
        }


        return "addFoodsToDay";

    }



    @GetMapping("/myCalendar/{foodDate}/addFood/{foodId}/save")
    public String saveFoodToDay(@PathVariable("foodDate") String date,@PathVariable("foodId") Integer foodId, Model model, String keyword) throws FoodNotFoundException {


        model.addAttribute("selectedDate",date);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);
        Food food = foodService.get(foodId);


        UsersFoods usersFoods = new UsersFoods();
        usersFoods.setUserId(user);
        usersFoods.setDate(date);
        usersFoods.setFoodId(food);

        usersFoodsService.save(usersFoods);


        return "redirect:/myCalendar/"+date;

    }


    @GetMapping("/myCalendar/{foodDate}/addRecipe/{recipeId}/save")
    public String saveRecipeToDay(@PathVariable("foodDate") String date,@PathVariable("recipeId") Integer recipeId, Model model, String keyword) throws RecipeNotFoundException {


        model.addAttribute("selectedDate",date);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);
        Recipe recipe = recipeService.get(recipeId);


        UsersRecipes usersRecipes = new UsersRecipes();
        usersRecipes.setUserId(user);
        usersRecipes.setDate(date);
        usersRecipes.setRecipeId(recipe);

        usersRecipesService.save(usersRecipes);


        return "redirect:/myCalendar/"+date;

    }



}
