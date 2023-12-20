package com.ru.prac7.controller;


import com.ru.prac7.model.*;
import com.ru.prac7.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allBooks() {
        ModelAndView modelAndView = new ModelAndView("index");

        List<Item> itemList = itemService.getAllItems();
        modelAndView.addObject("itemList", itemList);

        return modelAndView;
    }
}
