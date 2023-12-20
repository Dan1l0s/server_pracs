package com.ru.prac7.controller;

import com.ru.prac7.model.Item;
import com.ru.prac7.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/{sellerNumber}")
    public String postItem(@PathVariable int sellerNumber, @RequestBody Item item) {
        return itemService.createItem(sellerNumber, item);
    }

    @GetMapping(value = "/{sellerNumber}")
    public List<Item> allItems(@PathVariable int sellerNumber) {
        return itemService.getAllSellerItems(sellerNumber);
    }

    @GetMapping(value = "/{sellerNumber}/{id}")
    public String getItem(@PathVariable int sellerNumber, @PathVariable int id) {
        return itemService.getItemByIdJson(sellerNumber, id);
    }

    @PutMapping(value = "/{sellerNumber}/{id}")
    public String updateItem(@PathVariable int sellerNumber, @PathVariable int id, @RequestBody Item item) {
        return itemService.updateItem(sellerNumber, id, item);
    }

    @DeleteMapping(value = "/{sellerNumber}/{id}")
    public String deleteItem(@PathVariable int sellerNumber, @PathVariable int id) {
        return itemService.deleteItem(sellerNumber, id);
    }

    @PostMapping(value = "/all")
    public String postItemAdmin(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @GetMapping(value = "/all")
    public List<Item> allItemsAdmin() {
        return itemService.getAllSellerItems();
    }

    @GetMapping(value = "/all/{id}")
    public String getItemAdmin(@PathVariable int id) {
        return itemService.getItemByIdJson(id);
    }

    @PutMapping(value = "/all/{id}")
    public String updateItemAdmin(@PathVariable int id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping(value = "/all/{id}")
    public String deleteItemAdmin(@PathVariable int id) {
        return itemService.deleteItem(id);
    }


}