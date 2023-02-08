package com.chill.chillbbs.service.album;

import com.chill.chillbbs.entity.album.AlbumCollection;
import com.chill.chillbbs.entity.album.AlbumCommentLiked;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
public interface AlbumCommentLikedService {
    /**
     * 添加或更新
     *
     * @param albumCommentLiked 专辑评论喜爱信息
     * @return 专辑评论喜爱信息
     */
    @Async("chillPool")
    CompletableFuture<AlbumCommentLiked> saveOrUpdate(AlbumCommentLiked albumCommentLiked);

    /**
     * 通过id删除
     *
     * @param id 专辑评论喜爱信息id
     */
    @Async("chillPool")
    void delete(Long id);

    /**
     * 删除所有对应专辑的评论喜爱信息
     *
     * @param albumCommentId 专辑评论id
     */
    @Async("chillPool")
    void deleteAll(Long albumCommentId);

    /**
     * 查找
     *
     * @param albumCommentId 专辑评论id
     * @param userId         用户id
     * @return 专辑评论喜爱信息
     */
    @Async("chillPool")
    CompletableFuture<Optional<AlbumCommentLiked>> find(Long albumCommentId, Long userId);
}
