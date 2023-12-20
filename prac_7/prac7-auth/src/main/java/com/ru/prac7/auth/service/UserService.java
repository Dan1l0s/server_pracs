package com.ru.prac7.auth.service;

import com.ru.prac7.auth.model.Role;
import com.ru.prac7.auth.model.User;
import com.ru.prac7.auth.repository.RedisRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    RedisRepository repository;

    public String add(User user) {
        User u = repository.find(user.getLogin());
        if (u==null) {
            repository.add(user);
            return "added";
        }
        return "error";
    }

    public String update(String login, User user) {
        User u = repository.find(login);
        if (u!=null && login.equals(user.getLogin())) {
            repository.add(user);
            return "updated";
        }
        return "error";
    }

    public String delete(String login) {
        User u = repository.find(login);
        if (u != null) {
            repository.delete(login);
            return "deleted";
        }
        return "error";
    }

    public User find(String login) {
        return repository.find(login);
    }

    public Map<Object, Object> findAll() {
        return repository.findAll();
    }

    public String upgradeRole(String login, int sellerNumber) {
        Map<Object, Object> users = this.findAll();
        Set<Object> keys = users.keySet();
        for (Object key : keys) {
            User u = (User) users.get(key);
            if (u.getSellerNumber() == sellerNumber) {
                return "error";
            }
        }

        User u = this.find(login);
        if (u!=null) {
            u.setSellerNumber(sellerNumber);
            Set<Role> roles = new HashSet<>(Arrays.asList(u.getRoles().toArray(new Role[0])));
            roles.add(Role.SELLER);
            u.setRoles(roles);
            this.update(login, u);
            return "upgraded";
        }
        return "error";
    }

}
