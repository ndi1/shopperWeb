package com.jh.shopperweb.controllers;

import com.jh.shopperweb.user.User;
import com.jh.shopperweb.user.UserNotFoundException;
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
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        service.save(user);
        return "redirect:/login";
    }



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

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user",new User());

        return "signup";
    }

    @GetMapping("/reset")
    public String reset(Model model){
        model.addAttribute("user",new User());

        return "reset";
    }


    @GetMapping("/home")
    public String showHome(){

        return "home";
    }

    @GetMapping("/users/myGoals")
    public String showUserGoals(){

        return "userProfile";
    }

}
