package com.jh.shopperweb.controllers;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.recipe.Recipe;
import com.jh.shopperweb.user.MyUserDetailsService;
import com.jh.shopperweb.user.User;
import com.jh.shopperweb.user.UserNotFoundException;
import com.jh.shopperweb.user.UserService;
import com.jh.shopperweb.users_goals.UsersGoals;
import com.jh.shopperweb.users_goals.UsersGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

//Controller that handles the viewing, creation, and editing/deleting of a user
@Controller
public class UserController {
    @Autowired private UserService service;

    @Autowired private MyUserDetailsService userDetailsService;

    @Autowired private UsersGoalsService usersGoalsService;


    //Mapping to display all users
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    //Mapping to show the create user form
    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }

    //Mapping to save a user
    @PostMapping("/users/save")
    public String saveUser(User user){
        service.save(user);
        Integer userId = user.getUserId();
        UsersGoals usersGoals = new UsersGoals();
        usersGoals.setUser(user);
        usersGoals.setCalorieGoal(2000.0);
        usersGoals.setCarbGoal(200.0);
        usersGoals.setFatGoal(67.0);
        usersGoals.setProteinGoal(150.0);
        usersGoalsService.save(usersGoals);
        return "redirect:/login";
    }

    //Mapping to edit a user
    @GetMapping("/users/edit/{userId}")
    public String showEditForm(@PathVariable("userId") Integer userId, Model model, RedirectAttributes ra){
        try {
           User user = service.get(userId);
           model.addAttribute("user",user);
            model.addAttribute("pageTitle","Edit User (ID: " + userId +")");
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }

    //Mapping to delete a user
    @GetMapping("/users/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId, RedirectAttributes ra){
        try {
            service.delete(userId);
    ra.addFlashAttribute("message", "The user ID " + userId + " has been deleted.");

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/users";
    }

    //Mapping that handles signing up
    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user",new User());

        return "signup";
    }

    //Mapping to display the reset password page
    @GetMapping("/reset")
    public String reset(Model model){
        model.addAttribute("user",new User());

        return "reset";
    }

    //Mapping to go to the home page
    @GetMapping("/home")
    public String showHome(){

        return "redirect:myCalendar";
    }

    //Mapping to display the user goals form
    @GetMapping("/users/myGoals")
    public String showUserGoals(Model model){

        if (usersGoalsService.findUserGoals() != null){
            UsersGoals usersGoals = usersGoalsService.findUserGoals();
            model.addAttribute("goals",usersGoals);
        } else{
            model.addAttribute("goals",new UsersGoals());
        }




        return "userProfile";
    }

    //Mapping to save a user's goals
    @PostMapping("/myGoals/save")
    public String saveRecipes(UsersGoals usersGoals){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = userDetailsService.loadUser(curUserName);
        usersGoals.setUser(user);

        usersGoalsService.save(usersGoals);

        return "redirect:/users/myGoals";
    }


}
