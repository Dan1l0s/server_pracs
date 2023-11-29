package com.ru.prac5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telephones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="model")
    private String model;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="productType")
    private String productType;

    @Column(name="batteryCapacity")
    private int batteryCapacity;

    @Column(name="sellerNumber")
    private int sellerNumber;

    @Column(name="cost")
    private int cost;

    @Column(name="amount")
    private int amount;

    public void replace(Telephone t) {
        this.model = t.getModel();
        this.manufacturer = t.getManufacturer();
        this.productType = t.getProductType();
        this.batteryCapacity = t.getBatteryCapacity();
        this.sellerNumber = t.getSellerNumber();
        this.cost = t.getCost();
        this.amount = t.getAmount();
    }
}
