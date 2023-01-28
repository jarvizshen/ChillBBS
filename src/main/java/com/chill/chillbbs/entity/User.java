package com.chill.chillbbs.entity;

import com.chill.chillbbs.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 * @author Jarviz
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String username;

    private String password;

    private String photo;

    private String nickname;

    private String sign;

    private String sex;

    private String tel;

    private String role;

    private Date createTime;

    private Date updateTime;


    public UserDto toDto() {
        return new UserDto(getId(), getNickname(), getRole());
    }
}
