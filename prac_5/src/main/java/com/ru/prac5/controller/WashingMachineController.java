package com.ru.prac5.controller;

import com.ru.prac5.model.WashingMachine;
import com.ru.prac5.service.WashingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WashingMachineController {

    @Autowired
    private WashingMachineService washingMachineService;

    @PostMapping(value = "/washingMachine")
    public String postWashingMachine(@RequestBody WashingMachine washingMachine) {
        return washingMachineService.createWashingMachine(washingMachine);
    }

    @GetMapping(value = "/washingMachine/{id}")
    public String getWashingMachine(@PathVariable int id) {
        return washingMachineService.getWashingMachineById(id);
    }

    @PutMapping(value = "washingMachine/{id}")
    public String updateWashingMachine(@PathVariable int id, @RequestBody WashingMachine washingMachine) {
        return washingMachineService.updateWashingMachine(id, washingMachine);
    }

    @DeleteMapping(value = "/washingMachine/{id}")
    public String deleteWashingMachine(@PathVariable int id) {
        return washingMachineService.deleteWashingMachine(id);
    }




}