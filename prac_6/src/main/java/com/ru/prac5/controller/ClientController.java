package com.ru.prac5.controller;

import com.ru.prac5.model.Client;
import com.ru.prac5.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/client")
    public String postClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping(value = "/client/{id}")
    public String getClient(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @PutMapping(value = "client/{id}")
    public String updateClient(@PathVariable int id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping(value = "/client/{id}")
    public String deleteClient(@PathVariable int id) {
        return clientService.deleteClient(id);
    }




}