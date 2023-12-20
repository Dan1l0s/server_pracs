package com.ru.prac7.service;

import com.ru.prac7.model.Cart;
import com.ru.prac7.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private ItemService itemService;

    private Cart cart = new Cart();

    public String addItem(int id) {
        int cartAmount = cart.getItemAmount(id);
        int realAmount = itemService.getAmount(id);

        if (realAmount > cartAmount) {
            cart.addItem(id);
            return "added";
        } else {
            return "out of stock";
        }
    }

    public String removeItem(int id) {
        int cartAmount = cart.getItemAmount(id);
        if (cartAmount >= 1) {
            cart.removeItem(id);
            return "removed";
        } else {
            return "no such item in cart";
        }
    }

  

    public List<Item> getAllItems() {
        Map<Integer, Integer> itemsId = cart.getItems();
        List<Item> Items = new ArrayList<>();

        for (int key : itemsId.keySet()) {
            Item Item = itemService.getItemById(key);
            if (Item != null) {
                Item.setAmount(itemsId.get(key));
                Items.add(Item);
            }
        }
        return Items;
    }

   

    public void clearCart() {
        this.cart = new Cart();
    }

    public String makeOrder() {
        Map<Integer, Integer> itemsId = cart.getItems();

        if (itemsId.isEmpty()) {
            return "your cart is empty";
        }

        for (int key : itemsId.keySet()) {
            Item item = itemService.getItemById(key);
            int realAmount = item.getAmount();
            int cartAmount = cart.getItemAmount(key);
            if (realAmount < cartAmount) {
                return "item '" + item.getName() + "' is out of stock";
            }
            itemService.updateAmount(key, realAmount - cartAmount);
        }
        this.clearCart();
        return "ordered";
    }


}
