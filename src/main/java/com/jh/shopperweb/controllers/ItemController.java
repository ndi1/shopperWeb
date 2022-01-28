package com.jh.shopperweb.controllers;


import com.jh.shopperweb.item.Item;
import com.jh.shopperweb.item.ItemNotFoundException;
import com.jh.shopperweb.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private ItemService service;


    @GetMapping("/myItems")
    public String showItemList(Model model) {
        List<Item> listItems = service.listAll();
        model.addAttribute("listItems", listItems);
        return "items";
    }


    @GetMapping("/addItems")
    public String showNewItemForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("pageTitle", "Add New Item");
        return "addItems";
    }

    @PostMapping("/myItems/save")
    public String saveItem(Item item) {
        service.save(item);
        return "redirect:/myItems";
    }

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

