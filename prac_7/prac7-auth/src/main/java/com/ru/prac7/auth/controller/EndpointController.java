package com.ru.prac7.auth.controller;

import com.ru.prac7.auth.model.Role;
import com.ru.prac7.auth.service.AuthService;
import com.ru.prac7.auth.service.CartAdapter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class EndpointController {

    private final CartAdapter cartAdapter;

    @Autowired
    private AuthService authService;

    @ResponseBody
    @RequestMapping(value={"/item", "/item/{value2}"},
                    method = {RequestMethod.GET, RequestMethod.DELETE})
    public String getItem(HttpServletRequest request) {
        String url = request.getRequestURI();

        if (!authService.getRole().contains(Role.ADMIN)) {

            int sellerNumber = authService.getSellerNumber();
            url = url.replace("/item", "/item/" + sellerNumber);
        }
        else {
            url = url.replace("/item", "/item/all");
        }

        return cartAdapter.request(url, request.getMethod());
    }

    @ResponseBody
    @RequestMapping(value={"/item", "/item/{value2}"},
            method = {RequestMethod.POST, RequestMethod.PUT})
    public String postItem(HttpServletRequest request, @RequestBody Object object) {
        String url = request.getRequestURI();

        if (!authService.getRole().contains(Role.ADMIN)) {
            int sellerNumber = authService.getSellerNumber();
            url = url.replace("item", "item/" + sellerNumber);
        }
        else {
            url = url.replace("/item", "/item/all");
        }

        return cartAdapter.request(url, request.getMethod(), object);
    }

    @ResponseBody
    @GetMapping(value={"/cart", "/cart/{value1}", "/cart/{value1}/{value2}"})
    public String getCart(HttpServletRequest request) {
        return cartAdapter.request(request.getRequestURI(), "GET");
    }


}
