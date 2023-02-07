package com.chill.chillbbs.repository.album;

import com.chill.chillbbs.entity.album.Album;
import com.chill.chillbbs.entity.album.AlbumCollection;
import com.chill.chillbbs.entity.album.AlbumComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jarviz
 */
@Repository

public interface AlbumCollectionRepository extends JpaRepository<AlbumCollection, Long> {
    /**
     * 获取所有对应专辑的收藏信息
     *
     * @param albumId 专辑id
     * @return 所有对应信息
     */
    List<AlbumCollection> findAllByAlbumId(Long albumId);
}
