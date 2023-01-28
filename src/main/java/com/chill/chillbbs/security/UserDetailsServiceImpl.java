package com.chill.chillbbs.security;

import com.chill.chillbbs.entity.User;
import com.chill.chillbbs.repository.UserRepository;
import com.chill.chillbbs.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jarviz
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = accountService.getByUsername(username);
        if (userEntity != null) {
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userEntity.getRole()));
            return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), authorities);
        }
        return null;
    }
}
