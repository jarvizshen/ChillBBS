package com.chill.chillbbs.repository;

import com.chill.chillbbs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户rep
 *
 * @author 沈建树
 */
@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户实体
     */
    User findByUsername(String username);
}
