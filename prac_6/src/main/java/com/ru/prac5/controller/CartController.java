package com.ru.prac5.controller;

import com.ru.prac5.model.Book;
import com.ru.prac5.model.Client;
import com.ru.prac5.model.Telephone;
import com.ru.prac5.model.WashingMachine;
import com.ru.prac5.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    public CartService cartService;

    @GetMapping(value = "/book/add/{id}")
    public String addBook(@PathVariable int id) {
        cartService.addBook(id);
        return "redirect:/";
    }

    @GetMapping(value = "/book/remove/{id}")
    public String removeBook(@PathVariable int id) {
        cartService.removeBook(id);
        return "redirect:/";
    }

    @GetMapping(value = "/telephone/add/{id}")
    public String addTelephone(@PathVariable int id) {
        cartService.addTelephone(id);
        return "redirect:/";
    }

    @GetMapping(value = "/telephone/remove/{id}")
    public String removeTelephone(@PathVariable int id) {
        cartService.removeTelephone(id);
        return "redirect:/";
    }
    @GetMapping(value = "/washingMachine/add/{id}")
    public String addWashingMachine(@PathVariable int id) {
        cartService.addWashingMachine(id);
        return "redirect:/";
    }

    @GetMapping(value = "/washingMachine/remove/{id}")
    public String removeWashingMachine(@PathVariable int id) {
        cartService.removeWashingMachine(id);
        return "redirect:/";
    }

    @GetMapping(value = "/cart")
    public ModelAndView cart() {
        ModelAndView modelAndView = new ModelAndView("cart");

        List<Book> bookList = cartService.getAllBooks();
        modelAndView.addObject("bookList", bookList);

        List<Telephone> telephoneList = cartService.getAllTelephones();
        modelAndView.addObject("telephoneList", telephoneList);

        List<WashingMachine> washingMachineList = cartService.getAllWashingMachines();
        modelAndView.addObject("washingMachineList", washingMachineList);

        return modelAndView;
    }

    @GetMapping(value = "/clearCart")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }

    @ResponseBody
    @GetMapping(value = "/makeOrder")
    public String makeOrder() {
        return cartService.makeOrder();
    }
}
