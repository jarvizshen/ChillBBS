package com.chill.chillbbs.entity.album;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String albumName;
    private String artist;
    private long commentNum;
    private String type;
    private String picture;
    private Integer publishYear;
}
