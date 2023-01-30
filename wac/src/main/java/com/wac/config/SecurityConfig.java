package com.wac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration 
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
        
    }

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Spring Security는 GET 방식을 제외한 POST/PUT/DELETE 요청에서 CSRF 토큰을 요구.
        // POST/PUT/DELETE 요청에서 CSRF 토큰을 서버로 전송하지 않으면 403 에러(권한없음) 발생
        // 기능 구현을 간단하게 하기 위해서 Spring Security의 CSRF 기능을 비활성화.
        http.csrf().disable();
        
        // 로그인 설정
        http.formLogin()
        .loginPage("/user/signIn")
        .defaultSuccessUrl("/", true)
        .failureForwardUrl("/signInErr");
        
        
        http.logout() // 로그아웃 관련 설정 시작
        .logoutSuccessUrl("/user/signIn"); // 로그아웃 성공 후 이동할 url


        
        
        return http.build();
    }
    
}