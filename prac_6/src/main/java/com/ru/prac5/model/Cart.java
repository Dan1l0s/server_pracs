package com.ru.prac5.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Cart {

    private Map<Integer, Integer> books;
    private Map<Integer, Integer> telephones;
    private Map<Integer, Integer> washingMachines;

    public Cart() {
        this.books = new HashMap<>();
        this.telephones = new HashMap<>();
        this.washingMachines = new HashMap<>();
    }

    public void addBook(int id) {
        if (books.get(id) == null) {
            books.put(id, 1);
        }
        else {
            int temp = books.get(id)+1;
            books.replace(id, temp);
        }
    }

    public void removeBook(int id) {
        if (books.get(id) == 1) {
            books.remove(id);
        }
        else {
            int temp = books.get(id)-1;
            books.replace(id, temp);
        }
    }

    public int getBookAmount(int id) {
        if (books.get(id)==null)
            return 0;
        return books.get(id);
    }


    public void addTelephone(int id) {
        if (telephones.get(id) == null) {
            telephones.put(id, 1);
        }
        else {
            int temp = telephones.get(id)+1;
            telephones.replace(id, temp);
        }
    }

    public void removeTelephone(int id) {
        if (telephones.get(id) == 1) {
            telephones.remove(id);
        }
        else {
            int temp = telephones.get(id)-1;
            telephones.replace(id, temp);
        }
    }

    public int getTelephoneAmount(int id) {
        if (telephones.get(id)==null)
            return 0;
        return telephones.get(id);
    }

    public void addWashingMachine(int id) {
        if (washingMachines.get(id) == null) {
            washingMachines.put(id, 1);
        }
        else {
            int temp = washingMachines.get(id)+1;
            washingMachines.replace(id, temp);
        }
    }

    public void removeWashingMachine(int id) {
        if (washingMachines.get(id) == 1) {
            washingMachines.remove(id);
        }
        else {
            int temp = washingMachines.get(id)-1;
            washingMachines.replace(id, temp);
        }
    }

    public int getWashingMachineAmount(int id) {
        if (washingMachines.get(id)==null)
            return 0;
        return washingMachines.get(id);
    }



}
