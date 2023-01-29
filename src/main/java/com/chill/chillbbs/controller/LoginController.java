package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.User;
import com.chill.chillbbs.service.AccountService;
import com.chill.chillbbs.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 登录接口
 *
 * @author Jarviz
 */
@RestController
@RequestMapping("/api")
public class LoginController {
    @Resource
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Resource
    PasswordEncoder bCryptPasswordEncoder;
    @Resource
    TokenService tokenService;
    @Resource
    AccountService accountService;

    @PostMapping("/login")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Map<String, String>>> login(@RequestBody User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        org.springframework.security.core.userdetails.User user1 = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        System.out.println(user1.getAuthorities());
        Map<String, String> result = new HashMap<>(1);
        result.put("token", tokenService.getToken(user1.getUsername()));
        return CompletableFuture.completedFuture(ResponseEntity.ok(result));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try {
            return ResponseEntity.ok(accountService.saveOrUpdate(user).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
