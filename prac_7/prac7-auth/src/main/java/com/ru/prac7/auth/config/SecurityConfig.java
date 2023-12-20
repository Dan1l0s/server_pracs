package com.ru.prac7.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/refresh")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/*")).hasAuthority("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/user")).hasAuthority("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/item/*")).hasAnyAuthority("ADMIN", "SELLER")
                                .requestMatchers(new AntPathRequestMatcher("/item")).hasAnyAuthority("ADMIN", "SELLER")
                                .requestMatchers(new AntPathRequestMatcher("/cart/*")).hasAnyAuthority("ADMIN", "SELLER", "USER")
                                .requestMatchers(new AntPathRequestMatcher("/cart")).hasAnyAuthority("ADMIN", "SELLER", "USER")
                                .requestMatchers(new AntPathRequestMatcher("/upgrade/*")).hasAuthority("USER")
                                .anyRequest().authenticated()
                                //.anyRequest().permitAll()
                );
        http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

