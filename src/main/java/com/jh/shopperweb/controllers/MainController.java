package com.jh.shopperweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Controller for login/logout/homepage
@Controller
public class MainController {

    @GetMapping("")
    public String showHomePage(){
        return "redirect:myCalendar";
    }

    @GetMapping("/login")
    public String login(){

        return "index";

    }

    @GetMapping("/logout")
    public String logout(){

        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "signup";
    }


}
