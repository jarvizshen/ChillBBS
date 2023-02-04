package com.chill.chillbbs.service;

import com.chill.chillbbs.entity.User;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * 账户服务
 *
 * @author Jarviz
 */
public interface AccountService {
    /**
     * 查询所有账户
     *
     * @return 账户列表
     */
    @Async("chillPool")
    @SneakyThrows
    CompletableFuture<List<User>> getAccounts();

    /**
     * 根据账户id删除用户
     *
     * @param id id
     * @return 是否删除成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> deleteAccountById(long id);

    /**
     * 根据id查找用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Async("chillPool")
    @SneakyThrows
    CompletableFuture<Optional<User>> getById(Long id);

    /**
     * 添加或更新用户信息
     *
     * @param user 用户信息
     * @return 用户信息
     */
    @Async("chillPool")
    @SneakyThrows
    CompletableFuture<User> saveOrUpdate(User user);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户id
     * @return 用户信息
     */
    @SneakyThrows
    User getByUsername(String username);
}
