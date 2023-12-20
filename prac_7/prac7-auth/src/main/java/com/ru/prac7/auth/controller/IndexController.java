package com.ru.prac7.auth.controller;

import com.ru.prac7.auth.model.JwtRefreshRequest;
import com.ru.prac7.auth.model.JwtRequest;
import com.ru.prac7.auth.model.JwtResponse;
import com.ru.prac7.auth.model.Role;
import com.ru.prac7.auth.service.AuthService;
import com.ru.prac7.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
public class IndexController {

    @Autowired
    private final AuthService authService;
    @Autowired
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody JwtRefreshRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/upgrade/{sellerNumber}")
    public String upgradeUser(@PathVariable int sellerNumber) {
        if (authService.getRole().contains(Role.USER)) {
            String login = authService.getAuthInfo().getLogin();
            return userService.upgradeRole(login, sellerNumber);
        }
        return "error";
    }
}