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
import com.jh.shopperweb.users_foods.UsersFoodsNotFoundException;
import com.jh.shopperweb.users_foods.UsersFoodsService;
import com.jh.shopperweb.users_goals.UsersGoals;
import com.jh.shopperweb.users_goals.UsersGoalsService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private UsersGoalsService usersGoalsService;

    @GetMapping("/myCalendar")
    public String showCalendar(Model model){

        Date selectedDate = new Date();
        model.addAttribute("selectedDate", selectedDate);
        model.addAttribute("data",new DiaryFormData());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String date = dateFormat.format(selectedDate);

       return "redirect:/myCalendar/"+date;
    }


    @PostMapping("/myCalendar/load")
    public String foodDiaryDate(DiaryFormData data){
        return "redirect:/myCalendar/"+data.getDiaryDate();
    }


    @GetMapping("/myCalendar/{foodDate}")
    public String showFoodDate(@PathVariable("foodDate") String foodDate, Model model) throws ParseException {

            //Retrieve user's id and username
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String curUserName = authentication.getName();
            User user = myUserDetailsService.loadUser(curUserName);
            Integer curId = user.getUserId();

            //Parse date from URL
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(foodDate);
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String americanDate = formatter.format(date);

            //Populate model with initial values and form data.
            model.addAttribute("americanDate",americanDate);
            model.addAttribute("selectedDate",foodDate);
            model.addAttribute("data", new DiaryFormData());
            DiaryFormData diaryFormData = new DiaryFormData();
            diaryFormData.setDiaryDate(foodDate);

            //Load user's goals and put them into the model
           UsersGoals usersGoals = usersGoalsService.findUserGoals();
           Double calGoal = usersGoals.getCalorieGoal();
           Double carbGoal = usersGoals.getCarbGoal();
           Double proteinGoal = usersGoals.getProteinGoal();
           Double fatsGoal = usersGoals.getFatGoal();

           model.addAttribute("calGoal", calGoal);
           model.addAttribute("carbGoal",carbGoal);
           model.addAttribute("proteinGoal", proteinGoal);
           model.addAttribute("fatsGoal", fatsGoal);


        //Calculate calories used per day and display the percentage relative to their daily user goal
            Double foodCals = foodService.sumCalories(foodDate,curId);
            Double recipeCalories = recipeService.sumRecipeCalories(foodDate,curId);

            Double remainingCal = calGoal - foodCals-recipeCalories;
            Double calPercent = remainingCal/calGoal*100;
            BigDecimal bd = new BigDecimal(calPercent).setScale(2, RoundingMode.HALF_UP);
            calPercent = bd.doubleValue();
            model.addAttribute("remainingCal",remainingCal);
            model.addAttribute("calPercent",calPercent);

        //Calculate carbs used per day and display the percentage relative to their daily user goal
            Double foodCarbs = foodService.sumCarbs(foodDate,curId);
            Double recipeCarbs = recipeService.sumRecipeCarbs(foodDate,curId);

            Double remainingCarbs = carbGoal - foodCarbs-recipeCarbs;
            Double carbPercent = remainingCarbs/carbGoal*100;
            BigDecimal bd1 = new BigDecimal(carbPercent).setScale(2, RoundingMode.HALF_UP);
            carbPercent = bd1.doubleValue();
            model.addAttribute("remainingCarbs",remainingCarbs);
            model.addAttribute("carbPercent",carbPercent);

        //Calculate protein used per day and display the percentage relative to their daily user goal
            Double foodProtein = foodService.sumProtein(foodDate,curId);
            Double recipeProtein = recipeService.sumRecipeProtein(foodDate,curId);

            Double remainingProtein = proteinGoal - foodProtein-recipeProtein;
            Double proteinPercent = remainingProtein/proteinGoal*100;
            BigDecimal bd2 = new BigDecimal(proteinPercent).setScale(2, RoundingMode.HALF_UP);
            proteinPercent = bd2.doubleValue();
            model.addAttribute("remainingProtein",remainingProtein);
            model.addAttribute("proteinPercent",proteinPercent);

        //Calculate fats used per day and display the percentage relative to their daily user goal
            Double foodFats = foodService.sumFats(foodDate,curId);
            Double recipeFats = recipeService.sumRecipeFats(foodDate,curId);

            Double remainingFats = fatsGoal - foodFats-recipeFats;
            Double fatPercent = remainingFats/fatsGoal*100;
            BigDecimal bd3 = new BigDecimal(fatPercent).setScale(2, RoundingMode.HALF_UP);
            fatPercent = bd3.doubleValue();
            model.addAttribute("remainingFats",remainingFats);
            model.addAttribute("fatPercent",fatPercent);

        //Calculate fats used per day and display the percentage relative to their daily user goal
            Double foodPrice = foodService.sumPrice(foodDate,curId);
            Double recipePrice = recipeService.sumRecipePrice(foodDate,curId);
            model.addAttribute("totalPrice",foodPrice+recipePrice);



        List<Map<String,Object>> listRecipes = recipeService.macroTotalForRecipes(foodDate,curId);
        model.addAttribute("listRecipes",listRecipes);


        //Add user's daily foods and recipes to the model
        List<Food> foodsByDate = foodService.findUserFoodByDate(foodDate,curId);
        model.addAttribute("foodsByDate", foodsByDate);

        List<Recipe> recipesByDate = recipeService.findUserRecipesByDate(foodDate,curId);
        model.addAttribute("recipesByDate",recipesByDate);


        return "foodDiary";
    }


    @GetMapping("/myCalendar/{foodDate}/addRecipe")
    public String showAddRecipeToDay(@PathVariable("foodDate") String date, Model model, String keyword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);
        Integer curId = user.getUserId();


        model.addAttribute("selectedDate",date);

        if (keyword != null){
            model.addAttribute("listRecipes",recipeService.findByKeyword(keyword));
        }
        else{
            List<Map<String,Object>> listRecipes = recipeService.macrosByRecipe();
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


    @GetMapping("/myCalendar/{foodDate}/{foodId}/delete")
    public String deleteFoodFromDiary(@PathVariable("foodDate") String foodDate, @PathVariable("foodId")Integer foodId) throws UsersFoodsNotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);
        Integer curId = user.getUserId();

        usersFoodsService.deleteFromDay(foodDate,curId,foodId);


        return "redirect:/myCalendar/"+foodDate;
    }

    @GetMapping("/myCalendar/{foodDate}/{recipeId}/recipe/delete")
    public String deleteRecipeFromDiary(@PathVariable("foodDate") String foodDate, @PathVariable("recipeId")Integer recipeId) throws UsersFoodsNotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);
        Integer curId = user.getUserId();

        usersRecipesService.deleteFromDay(foodDate,curId,recipeId);


        return "redirect:/myCalendar/"+foodDate;
    }




}
