package com.chill.chillbbs.service;

import org.springframework.scheduling.annotation.Async;

/**
 * token服务
 * @author Jarviz
 */
public interface TokenService {
    /**
     * 生成token并返回
     * @param username 用户名
     * @return token
     */
    String getToken(String username);

    /**
     * 根据token获得用户名
     * @param token token
     * @return 用户名
     */
    String getUsername(String token);

    /**
     * 验证token
     * @param token token
     * @return 是否合法
     */
    boolean validateToken(String token);
}
