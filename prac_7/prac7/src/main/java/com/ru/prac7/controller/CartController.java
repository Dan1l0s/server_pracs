package com.ru.prac7.controller;

import com.ru.prac7.model.Item;
import com.ru.prac7.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    public CartService cartService;

    @GetMapping(value = "/add/{id}")
    public String addBook(@PathVariable int id) {
        return cartService.addItem(id);
    }

    @GetMapping(value = "/remove/{id}")
    public String removeBook(@PathVariable int id) {
        return cartService.removeItem(id);
    }

    @GetMapping(value = "")
    public List<Item> cart() {
        ModelAndView modelAndView = new ModelAndView("cart");
        return cartService.getAllItems();
    }

    @GetMapping(value = "/clear")
    public void clearCart() {
        cartService.clearCart();
        //return "redirect:/cart";
    }

    @ResponseBody
    @GetMapping(value = "/order")
    public String makeOrder() {
        return cartService.makeOrder();
    }


}
