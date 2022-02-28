package com.jh.shopperweb.controllers;


import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.item.Item;
import com.jh.shopperweb.item.ItemNotFoundException;
import com.jh.shopperweb.item.ItemService;
import com.jh.shopperweb.user.MyUserDetailsService;
import com.jh.shopperweb.user.User;
import com.jh.shopperweb.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
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

//Controller that handles the viewing, creation, and editing/deleting of an item
@Controller
public class ItemController {
    @Autowired
    private ItemService service;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    //Mapping which displays all user created items
    @GetMapping("/myItems")
    public String showItemList(Model model, String keyword) {

        if (keyword != null){
            model.addAttribute("listItems",service.findByKeyword(keyword));
        }
        else{
            List<Item> listItems = service.listAll();
            model.addAttribute("listItems",listItems);
        }

        return "items";
    }

    //Mapping which displays a user's custom items
    @GetMapping("/myItems/personal")
    public String showMyFoodList(Model model, String keyword){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);
        Integer curId = user.getUserId();

        if (keyword != null){
            model.addAttribute("listItems",service.findUserItemByKeyword(keyword,curId));
        }
        else{
            List<Item> listItems = service.listUserItems(curId);
            model.addAttribute("listItems",listItems);
        }

        return "itemsPersonal";
    }


    //Mapping to allow users to create a custom item
    @GetMapping("/addItems")
    public String showNewItemForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("pageTitle", "Add New Item");
        return "addItems";
    }

    //Mapping to save a custom item
    @PostMapping("/myItems/save")
    public String saveItem(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = myUserDetailsService.loadUser(curUserName);

        if(bindingResult.hasErrors()){
            return "addItems";
        }else{
            item.setUserItem(user);
            service.save(item);
            return "redirect:/myItems";
        }

    }

    //Mapping to edit a user created item
    @GetMapping("/myItems/edit/{itemId}")
    public String showItemEditForm(@PathVariable("itemId") Integer itemId, Model model, RedirectAttributes ra) {
        try {
            Item item = service.get(itemId);
            model.addAttribute("item", item);
            model.addAttribute("pageTitle", "Edit Item (ID: " + itemId + ")");
            return "addItems";
        } catch (ItemNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/myItems";

        }
    }

    //Mapping to delete a user created item
    @GetMapping("/myItems/delete/{itemId}")
    public String deleteItem(@PathVariable("itemId") Integer itemId, RedirectAttributes ra) {
        try {
            service.delete(itemId);
            ra.addFlashAttribute("message", "The Item ID " + itemId + " has been deleted.");

        } catch (ItemNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/myItems";
    }
}

