package com.ru.prac7.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "productType")
    private String productType;

    @Column(name = "sellerNumber")
    private int sellerNumber = 0;

    @Column(name = "cost")
    private int cost;

    @Column(name = "amount")
    private int amount;

    public void replace(Item i) {
        this.name = i.getName();
        this.manufacturer = i.getManufacturer();
        this.productType = i.getProductType();
        this.cost = i.getCost();
        this.amount = i.getAmount();
    }
}
