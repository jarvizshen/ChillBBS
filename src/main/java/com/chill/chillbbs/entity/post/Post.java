package com.chill.chillbbs.entity.post;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * 帖子实体类
 *
 * @author Jarviz
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String type;
    private String file;
    private int collected;

    private long commentNum;
    private long collectNum;
    private Date createTime;
    private Date updateTime;

}