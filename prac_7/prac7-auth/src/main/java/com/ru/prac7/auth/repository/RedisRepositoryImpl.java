package com.ru.prac7.auth.repository;

import com.ru.prac7.auth.model.Role;
import com.ru.prac7.auth.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
    private static final String KEY = "User";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOp;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOp = redisTemplate.opsForHash();
        List<User> users = Arrays.asList(
                new User("anton", "12345", Collections.singleton(Role.USER)),
                new User("admin", "root", Collections.singleton(Role.ADMIN)),
                new User("1111", "1234", 4455, Collections.singleton(Role.SELLER)),
                new User("2222", "4321", 1122, Collections.singleton(Role.SELLER))
        );
        for (User u : users) {
            this.add(u);
        }
    }

    public void add(User user) {
        hashOp.put(KEY, user.getLogin(), user);
    }

    public void delete(String login) {
        hashOp.delete(KEY, login);
    }

    public User find(String login){
        return (User) hashOp.get(KEY, login);
    }

    public Map<Object, Object> findAll(){
        return hashOp.entries(KEY);
    }

}