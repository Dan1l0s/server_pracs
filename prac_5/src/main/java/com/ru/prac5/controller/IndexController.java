package com.ru.prac5.controller;


import com.ru.prac5.model.*;
import com.ru.prac5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TelephoneService telephoneService;
    @Autowired
    private WashingMachineService washingMachineService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allBooks() {
        ModelAndView modelAndView = new ModelAndView("index");

        List<Book> bookList = bookService.getAllBooks();
        modelAndView.addObject("bookList", bookList);

        List<Client> clientList = clientService.getAllClients();
        modelAndView.addObject("clientList", clientList);

        List<Telephone> telephoneList = telephoneService.getAllTelephones();
        modelAndView.addObject("telephoneList", telephoneList);

        List<WashingMachine> washingMachineList = washingMachineService.getAllWashingMachines();
        modelAndView.addObject("washingMachineList", washingMachineList);

        return modelAndView;
    }
}
