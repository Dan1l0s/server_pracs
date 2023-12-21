package com.ru.prac5.service;

import com.ru.prac5.model.Cart;
import com.ru.prac5.model.Book;
import com.ru.prac5.model.Telephone;
import com.ru.prac5.model.WashingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private BookService bookService;
    @Autowired
    private TelephoneService telephoneService;
    @Autowired
    private WashingMachineService washingMachineService;

    private Cart cart = new Cart();

    public String addBook(int id) {
        int cartAmount = cart.getBookAmount(id);
        int realAmount = bookService.getAmount(id);

        if (realAmount > cartAmount) {
            cart.addBook(id);
            return "added";
        } else {
            return "out of stock";
        }
    }

    public String removeBook(int id) {
        int cartAmount = cart.getBookAmount(id);
        if (cartAmount >= 1) {
            cart.removeBook(id);
            return "removed";
        } else {
            return "no such item in cart";
        }
    }

    public String addTelephone(int id) {
        int cartAmount = cart.getTelephoneAmount(id);
        int realAmount = telephoneService.getAmount(id);

        if (realAmount > cartAmount) {
            cart.addTelephone(id);
            return "added";
        } else {
            return "out of stock";
        }
    }

    public String removeTelephone(int id) {
        int cartAmount = cart.getTelephoneAmount(id);
        if (cartAmount >= 1) {
            cart.removeTelephone(id);
            return "removed";
        } else {
            return "no such item in cart";
        }
    }

    public String addWashingMachine(int id) {
        int cartAmount = cart.getWashingMachineAmount(id);
        int realAmount = washingMachineService.getAmount(id);

        if (realAmount > cartAmount) {
            cart.addWashingMachine(id);
            return "added";
        } else {
            return "out of stock";
        }
    }

    public String removeWashingMachine(int id) {
        int cartAmount = cart.getWashingMachineAmount(id);
        if (cartAmount >= 1) {
            cart.removeWashingMachine(id);
            return "removed";
        } else {
            return "no such item in cart";
        }
    }

    public List<Book> getAllBooks() {
        Map<Integer, Integer> booksId = cart.getBooks();
        List<Book> books = new ArrayList<>();

        for (int key : booksId.keySet()) {
            Book book = bookService.getBookById(key);
            if (book != null) {
                book.setAmount(booksId.get(key));
                books.add(book);
            }
        }
        return books;
    }

    public List<Telephone> getAllTelephones() {
        Map<Integer, Integer> telephonesId = cart.getTelephones();
        List<Telephone> telephones = new ArrayList<>();

        for (int key : telephonesId.keySet()) {
            Telephone telephone = telephoneService.getTelephoneById(key);
            if (telephone != null) {
                telephone.setAmount(telephonesId.get(key));
                telephones.add(telephone);
            }
        }
        return telephones;
    }

    public List<WashingMachine> getAllWashingMachines() {
        Map<Integer, Integer> washingMachinesId = cart.getWashingMachines();
        List<WashingMachine> washingMachines = new ArrayList<>();

        for (int key : washingMachinesId.keySet()) {
            WashingMachine washingMachine = washingMachineService.getWashingMachineById(key);
            if (washingMachine != null) {
                washingMachine.setAmount(washingMachinesId.get(key));
                washingMachines.add(washingMachine);
            }
        }
        return washingMachines;
    }

    public void clearCart() {
        this.cart = new Cart();
    }

    public String makeOrder() {
        Map<Integer, Integer> booksId = cart.getBooks();
        Map<Integer, Integer> telephonesId = cart.getBooks();
        Map<Integer, Integer> washingMachinesId = cart.getBooks();

        if (booksId.isEmpty() &&
                telephonesId.isEmpty() &&
                washingMachinesId.isEmpty()) {
            return "Your cart is empty";
        }

        for (int key : booksId.keySet()) {
            Book book = bookService.getBookById(key);
            int realAmount = book.getAmount();
            int cartAmount = cart.getBookAmount(key);
            if (realAmount < cartAmount) {
                return "Item '" + book.getName() + "' is out of stock";
            }
            bookService.updateAmount(key, realAmount - cartAmount);
        }
        for (int key : telephonesId.keySet()) {
            Telephone telephone = telephoneService.getTelephoneById(key);
            int realAmount = telephone.getAmount();
            int cartAmount = cart.getTelephoneAmount(key);
            if (realAmount < cartAmount) {
                return "Item '" + telephone.getModel() + "' is out of stock";
            }
            telephoneService.updateAmount(key, realAmount - cartAmount);
        }
        for (int key : washingMachinesId.keySet()) {
            WashingMachine washingMachine = washingMachineService.getWashingMachineById(key);
            int realAmount = washingMachine.getAmount();
            int cartAmount = cart.getWashingMachineAmount(key);
            if (realAmount < cartAmount) {
                return "Item '" + washingMachine.getManufacturer() + ", " +
                        washingMachine.getTankCapacity() + "' is out of stock";
            }
            washingMachineService.updateAmount(key, realAmount - cartAmount);
        }
        this.clearCart();
        return "Order was placed";
    }


}
