package com.jh.shopperweb.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Date;


@Controller
public class CalendarController {

    @GetMapping("/myCalendar")
    public String showCalendar(Model model){

        Date selectedDate = new Date();
        model.addAttribute("selectedDate", selectedDate);

        return "calendar";
    }

}
