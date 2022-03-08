package com.jh.shopperweb.controllers;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodNotFoundException;
import com.jh.shopperweb.food.FoodService;
import com.jh.shopperweb.recipe.Recipe;
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
import java.util.List;

//Controller that handles the viewing, creation, and editing/deleting of a food
@Controller
public class FoodController {
    @Autowired private FoodService service;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    //Display all user created foods
    @GetMapping("/myFoods")
    public String showAllFoodList(Model model, String keyword){


        if (keyword != null){
            model.addAttribute("listFoods",service.findByKeyword(keyword));
        }
        else{
            List<Food> listFoods = service.listAll();
            model.addAttribute("listFoods",listFoods);
        }

        return "myFoods";
    }

    //Display the foods created by the currently logged in user
    @GetMapping("/myFoods/personal")
    public String showMyFoodList(Model model, String keyword){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);
        Integer curId = user.getUserId();

        if (keyword != null){
            model.addAttribute("listFoods",service.findUserFoodByKeyword(keyword,curId));
        }
        else{
            List<Food> listFoods = service.listUserFoods(curId);
            model.addAttribute("listFoods",listFoods);
        }

        return "myFoodsPersonal";
    }

    //Mapping to create a new food
    @GetMapping("/addFoods")
    public String showNewFoodForm(Model model){
        model.addAttribute("food",new Food());
        model.addAttribute("pageTitle","Add New Food");
        return "addFoods";
    }

    //Mapping to save a user created food
    @PostMapping("/myFoods/save")
    public String saveFood(@Valid @ModelAttribute("food") Food food, BindingResult bindingResult){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);

        if(bindingResult.hasErrors()){
            return "addFoods";
        }else {
            food.setUser(user);
            service.save(food);
            return "redirect:/myFoods";
        }

    }

    //Mapping to edit a user created food
    @GetMapping("/myFoods/edit/{foodId}")
    public String showFoodEditForm(@PathVariable("foodId") Integer foodId, Model model, RedirectAttributes ra) {
        try {
            Food food = service.get(foodId);
            model.addAttribute("food", food);
            model.addAttribute("pageTitle", "Edit Food (ID: " + foodId + ")");
            return "addFoods";
        } catch (FoodNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/myFoods";

        }
    }

    //Mapping to delete a user created food
    @GetMapping("/myFoods/delete/{foodId}")
    public String deleteFood(@PathVariable("foodId") Integer foodId, RedirectAttributes ra){
        try {
            service.delete(foodId);
            ra.addFlashAttribute("message", "The Food ID " + foodId + " has been deleted.");

        } catch (FoodNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/myFoods";
    }


    //Mapping which displays the add food to day page
    @GetMapping("/myFoods/addToDiary")
    public String addFoodToDiary(Model model, String keyword){


        if (keyword != null){
            model.addAttribute("listFoods",service.findByKeyword(keyword));
        }
        else{
            List<Food> listFoods = service.listAll();
            model.addAttribute("listFoods",listFoods);
        }


        return "addFoodsToDay";
    }

}
