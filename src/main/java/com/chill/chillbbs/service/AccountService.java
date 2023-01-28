package com.chill.chillbbs.service;

import com.chill.chillbbs.entity.User;

import java.util.List;
import java.util.Optional;

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
    List<User> getAccounts();

    /**
     * 根据账户id删除用户
     *
     * @param id id
     * @return 是否删除成功
     */
    boolean deleteAccountById(long id);

    /**
     * 根据id查找用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    Optional<User> getById(Long id);
    /**
     * 根据用户名查找用户
     *
     * @param username 用户id
     * @return 用户信息
     */
    User getByUsername(String username);
}
