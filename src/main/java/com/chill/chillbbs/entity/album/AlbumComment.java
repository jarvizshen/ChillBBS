package com.chill.chillbbs.entity.album;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;

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
public class AlbumComment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private Long userId;
    private Long albumId;
    private Long likeNum;
    private String content;
    private Date createTime;
}
