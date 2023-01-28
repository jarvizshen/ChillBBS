package com.chill.chillbbs.service.impl;

import com.chill.chillbbs.entity.User;
import com.chill.chillbbs.repository.UserRepository;
import com.chill.chillbbs.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Jarviz
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    UserRepository userRepository;

    @Override
    public List<User> getAccounts() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteAccountById(long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
