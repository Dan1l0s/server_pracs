package com.ru.prac7.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartAdapter {

    private final RestTemplate restTemplate;

    @Value("${cart.url}")
    private String cartUrl;

    public CartAdapter(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public String request(String request, String method) {
        HttpEntity<Object> entity = new HttpEntity<Object>(null, new HttpHeaders());
        System.out.println(cartUrl+request);
        ResponseEntity<String> response;
        if (method.equals("GET")) {
            response = restTemplate.exchange(cartUrl+request, HttpMethod.GET, entity, String.class);
            return response.getBody();
        }
        if (method.equals("DELETE")) {
            response = restTemplate.exchange(cartUrl+request, HttpMethod.DELETE, entity, String.class);
            return response.getBody();
        }
        return "";
    }

    public String request(String request, String method, Object object) {
        HttpEntity<Object> entity = new HttpEntity<Object>(object, new HttpHeaders());
        ResponseEntity<String> response;
        if (method.equals("POST")) {
            response = restTemplate.exchange(cartUrl+request, HttpMethod.POST, entity, String.class);
            return response.getBody();
        }
        if (method.equals("PUT")) {
            response = restTemplate.exchange(cartUrl+request, HttpMethod.PUT, entity, String.class);
            return response.getBody();
        }
        return "";
    }
}