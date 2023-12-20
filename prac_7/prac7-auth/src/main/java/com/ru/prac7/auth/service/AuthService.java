package com.ru.prac7.auth.service;

import com.ru.prac7.auth.model.*;
import com.ru.prac7.auth.repository.JwtProvider;
import com.ru.prac7.auth.repository.RedisRepository;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RedisRepository userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userService.find(authRequest.getLogin());
        if (user!=null) {
            if (user.getPassword().equals(authRequest.getPassword())) {
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String refreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), refreshToken);
                return new JwtResponse(accessToken, refreshToken);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.find(login);
                if (user!=null) {
                    final String accessToken = jwtProvider.generateAccessToken(user);
                    return new JwtResponse(accessToken, null);
                }
            }
        }
        return new JwtResponse(null, null);
    }

//    public JwtResponse refresh(@NonNull String refreshToken) {
//        if (jwtProvider.validateRefreshToken(refreshToken)) {
//            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
//            final String login = claims.getSubject();
//            final String saveRefreshToken = refreshStorage.get(login);
//            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
//                final Optional<User> user = userService.getByLogin(login);
//                if (user.isPresent()) {
//                    final String accessToken = jwtProvider.generateAccessToken(user.get());
//                    final String newRefreshToken = jwtProvider.generateRefreshToken(user.get());
//                    refreshStorage.put(user.get().getLogin(), newRefreshToken);
//                    return new JwtResponse(accessToken, newRefreshToken);
//                }
//            }
//        }
//        return new JwtResponse(null, null);
//    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public Set<Role> getRole() {
        return (Set<Role>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    public int getSellerNumber() {
        return (int) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    public String getLogin() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}