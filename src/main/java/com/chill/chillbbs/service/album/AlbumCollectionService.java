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
     * 通过id删除
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
     * 按id查找
     *
     * @param id id
     * @return 收藏信息
     */
    @Async("chillPool")
    CompletableFuture<Optional<AlbumCollection>> find(Long id);
}
