package com.chill.chillbbs.service.album;

import com.chill.chillbbs.entity.album.AlbumCollection;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
public interface AlbumCollectionService {
    /**
     * 添加或更新
     *
     * @param albumCollection 收藏专辑
     * @return 收藏信息
     */
    @Async("chillPool")
    CompletableFuture<AlbumCollection> saveOrUpdate(AlbumCollection albumCollection);

    /**
     * 删除
     *
     * @param albumCollection 收藏专辑
     */
    @Async("chillPool")
    void delete(AlbumCollection albumCollection);

    /**
     * 删除所有对应专辑的收藏信息
     *
     * @param albumId 专辑id
     */
    @Async("chillPool")
    void deleteAll(Long albumId);

    /**
     * 查找
     *
     * @param albumId 专辑id
     * @param userId  用户id
     * @return 收藏信息
     */
    @Async("chillPool")
    CompletableFuture<Optional<AlbumCollection>> find(Long albumId, Long userId);
}
