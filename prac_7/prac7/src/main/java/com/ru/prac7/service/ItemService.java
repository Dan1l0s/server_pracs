package com.ru.prac7.service;

import com.google.gson.Gson;
import com.ru.prac7.model.Item;
import com.ru.prac7.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public String createItem(int sellerNumber, Item item) {
        item.setSellerNumber(sellerNumber);
        Optional<Item> existingItem = itemRepository.findById(item.getId());
        if (existingItem.isPresent()) {
            return "error";
        }
        itemRepository.save(item);
        return "created";
    }
    public String createItem(Item item) {
        item.setSellerNumber(9999);
        Optional<Item> existingItem = itemRepository.findById(item.getId());
        if (existingItem.isPresent()) {
            return "error";
        }
        itemRepository.save(item);
        return "created";
    }

    public List<Item> getAllItems() {
        return itemRepository.findAllByOrderByIdAsc();
    }

    public List<Item> getAllSellerItems(int sellerNumber) {
        List<Item> allItems = itemRepository.findAllByOrderByIdAsc();
        List<Item> items = new ArrayList<>();
        for (Item i : allItems) {
            if (i.getSellerNumber()== sellerNumber) {
                items.add(i);
            }
        }
        return items;
    }
    public List<Item> getAllSellerItems() {
        return itemRepository.findAllByOrderByIdAsc();
    }

    public Item getItemById(int id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return item.get();
        }
        return null;
    }

    public String getItemByIdJson(int sellerNumber, int id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            if (item.get().getSellerNumber() == sellerNumber) {
                Gson gson = new Gson();
                String json = gson.toJson(item.get());
                return json;
            }
        }
        return "error";
    }
    public String getItemByIdJson(int id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
                Gson gson = new Gson();
                String json = gson.toJson(item.get());
                return json;
        }
        return "error";
    }

    public String updateItem(int sellerNumber, int id, Item itemDetails) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            if (item.get().getSellerNumber() == sellerNumber) {
                Item existingItem = item.get();
                existingItem.replace(itemDetails);
                itemRepository.save(existingItem);
                return "updated";
            }
        }
        return "error";
    }
    public String updateItem(int id, Item itemDetails) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
                Item existingItem = item.get();
                existingItem.replace(itemDetails);
                itemRepository.save(existingItem);
                return "updated";
        }
        return "error";
    }

    public String deleteItem(int sellerNumber, int id) {
        Optional<Item> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()) {
            if (existingItem.get().getSellerNumber() == sellerNumber) {
                itemRepository.deleteById(id);
                return "deleted";
            }
        }
        return "error";
    }
    public String deleteItem(int id) {
        Optional<Item> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()) {
                itemRepository.deleteById(id);
                return "deleted";
        }
        return "error";
    }

    public int getAmount(int id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return item.get().getAmount();
        }
        return -1;
    }

    public void updateAmount(int id, int amount) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            item.get().setAmount(amount);
            itemRepository.save(item.get());
        }
    }
}
