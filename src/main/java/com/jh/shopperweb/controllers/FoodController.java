package com.jh.shopperweb.controllers;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodNotFoundException;
import com.jh.shopperweb.food.FoodRepository;
import com.jh.shopperweb.food.FoodService;
import com.jh.shopperweb.user.User;
import com.jh.shopperweb.user.UserNotFoundException;
import com.jh.shopperweb.user.UserRepository;
import com.jh.shopperweb.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FoodController {
    @Autowired private FoodService service;


    @GetMapping("/myFoods")
    public String showFoodList(Model model){
        List<Food> listFoods = service.listAll();
        model.addAttribute("listFoods",listFoods);
        return "myFoods";
    }


    @GetMapping("/addFoods")
    public String showNewFoodForm(Model model){
        model.addAttribute("food",new Food());
        model.addAttribute("pageTitle","Add New Food");
        return "addFoods";
    }

    @PostMapping("/myFoods/save")
    public String saveFood(Food food){
        service.save(food);
        return "redirect:/myFoods";
    }

    @GetMapping("/foods/edit/{id}")
    public String showFoodEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Food food = service.get(id);
            model.addAttribute("food", food);
            model.addAttribute("pageTitle", "Edit Food (ID: " + id + ")");
            return "addFoods";
        } catch (FoodNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/myFoods";

        }
    }

    @GetMapping("/myFoods/delete/{id}")
    public String deleteFood(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The Food ID " + id + " has been deleted.");

        } catch (FoodNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/myFoods";
    }


}
