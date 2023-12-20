package com.ru.prac7.auth.repository;

import com.ru.prac7.auth.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.Set;


public interface RedisRepository {

    Map<Object, Object> findAll();
    void add(User user);
    void delete(String login);
    User find(String login);
}