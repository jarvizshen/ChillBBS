package com.chill.chillbbs.repository.album;

import com.chill.chillbbs.entity.album.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jarviz
 */
@Repository

public interface AlbumRepository extends JpaRepository<Album, Long> {
    /**
     * 模糊检索专辑
     *
     * @param albumName 专辑名称
     * @return 匹配专辑
     */
    List<Album> findAllByAlbumNameLike(String albumName);
}
