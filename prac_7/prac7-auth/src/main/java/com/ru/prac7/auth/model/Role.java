package com.ru.prac7.auth.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    USER("USER"),
    SELLER("SELLER");

    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }

}