package com.ru.prac5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "washingMachines")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WashingMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="tankCapacity")
    private float tankCapacity;

    @Column(name="productType")
    private String productType;

    @Column(name="sellerNumber")
    private int sellerNumber;

    @Column(name="cost")
    private int cost;

    @Column(name="amount")
    private int amount;

    public void replace(WashingMachine w) {
        this.manufacturer = w.getManufacturer();
        this.tankCapacity = w.getTankCapacity();
        this.productType = w.getProductType();
        this.sellerNumber = w.getSellerNumber();
        this.cost = w.getCost();
        this.amount = w.getAmount();
    }
}

