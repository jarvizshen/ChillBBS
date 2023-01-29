package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.User;
import com.chill.chillbbs.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 账户服务接口
 *
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Resource
    AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        try {
            return ResponseEntity.ok(accountService.getAccounts().get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getById(Long id) {
        try {
            return ResponseEntity.ok(accountService.getById(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/getByUsername")
    public ResponseEntity<User> getById(String username) {
        return ResponseEntity.ok(accountService.getByUsername(username));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(long id) {
        try {
            return ResponseEntity.ok(accountService.deleteAccountById(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
