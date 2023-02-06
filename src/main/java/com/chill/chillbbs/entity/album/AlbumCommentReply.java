package com.chill.chillbbs.entity.album;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author Jarviz
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class AlbumCommentReply {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private Long userId;
    private Long commentId;
    private String content;
    private Date createTime;
}
