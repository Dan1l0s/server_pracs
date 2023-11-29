package com.ru.prac5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="author")
    private String author;

    @Column(name="productType")
    private String productType;

    @Column(name="sellerNumber")
    private int sellerNumber;

    @Column(name="cost")
    private int cost;

    @Column(name="amount")
    private int amount;

    public void replace(Book b) {
        this.name = b.getName();
        this.author = b.getAuthor();
        this.productType = b.getProductType();
        this.sellerNumber = b.getSellerNumber();
        this.cost =b.getCost();
        this.amount = b.getAmount();
    }
}
