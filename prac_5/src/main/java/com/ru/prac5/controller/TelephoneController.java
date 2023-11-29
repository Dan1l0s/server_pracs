package com.ru.prac5.controller;

import com.ru.prac5.model.Telephone;
import com.ru.prac5.service.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TelephoneController {

    @Autowired
    private TelephoneService telephoneService;

    @PostMapping(value = "/telephone")
    public String postTelephone(@RequestBody Telephone telephone) {
        return telephoneService.createTelephone(telephone);
    }

    @GetMapping(value = "/telephone/{id}")
    public String getTelephone(@PathVariable int id) {
        return telephoneService.getTelephoneById(id);
    }

    @PutMapping(value = "telephone/{id}")
    public String updateTelephone(@PathVariable int id, @RequestBody Telephone telephone) {
        return telephoneService.updateTelephone(id, telephone);
    }

    @DeleteMapping(value = "/telephone/{id}")
    public String deleteTelephone(@PathVariable int id) {
        return telephoneService.deleteTelephone(id);
    }




}