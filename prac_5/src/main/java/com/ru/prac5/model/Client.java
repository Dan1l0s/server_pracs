package com.ru.prac5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    public void replace(Client c) {
        this.name = c.getName();
        this.email = c.getEmail();
        this.login = c.getLogin();
        this.password = c.getPassword();
    }
}
