package com.ru.prac7.auth.model;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRefreshRequest {

    private String refreshToken;

}
