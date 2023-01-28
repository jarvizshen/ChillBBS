package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.User;
import com.chill.chillbbs.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * 账户服务接口
 *
 * @author Jarviz
 */
@RestController
@EnableAsync
@RequestMapping("/api/account")
public class AccountController {
    @Resource
    AccountService accountService;

    @Async("chillPool")
    @GetMapping("/all")
    public CompletableFuture<ResponseEntity<List<User>>> getAllUsers() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(accountService.getAccounts()));
    }

    @Async("chillPool")
    @GetMapping("/getById")
    public CompletableFuture<ResponseEntity<Optional<User>>> getById(Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(accountService.getById(id)));
    }
    @Async("chillPool")
    @GetMapping("/getByUsername")
    public CompletableFuture<ResponseEntity<User>> getById(String username) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(accountService.getByUsername(username)));
    }

    @DeleteMapping("/delete")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Boolean>> delete(long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(accountService.deleteAccountById(id)));
    }

}
