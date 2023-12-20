package com.ru.prac7.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Cart {

    private Map<Integer, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(int id) {
        if (items.get(id) == null) {
            items.put(id, 1);
        }
        else {
            int temp = items.get(id)+1;
            items.replace(id, temp);
        }
    }

    public void removeItem(int id) {
        if (items.get(id) == 1) {
            items.remove(id);
        }
        else {
            int temp = items.get(id)-1;
            items.replace(id, temp);
        }
    }

    public int getItemAmount(int id) {
        if (items.get(id)==null)
            return 0;
        return items.get(id);
    }
}
