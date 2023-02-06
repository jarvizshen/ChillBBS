package com.chill.chillbbs.service.impl;

import com.chill.chillbbs.entity.User;
import com.chill.chillbbs.repository.UserRepository;
import com.chill.chillbbs.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    UserRepository userRepository;

    @Override
    public CompletableFuture<List<User>> getAccounts() {
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    @Override
    public CompletableFuture<Boolean> deleteAccountById(long id) {
        try {
            userRepository.deleteById(id);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public CompletableFuture<Optional<User>> getById(Long id) {
        return CompletableFuture.completedFuture(userRepository.findById(id));
    }

    @Override
    public CompletableFuture<User> saveOrUpdate(User user) {
        user.setCreateTime(new Date());
        return CompletableFuture.completedFuture(userRepository.save(user));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
