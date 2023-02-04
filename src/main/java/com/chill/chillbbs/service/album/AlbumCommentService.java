package com.chill.chillbbs.service.album;

import com.chill.chillbbs.entity.album.AlbumComment;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 评论服务
 *
 * @author Jarviz
 */
public interface AlbumCommentService {
    /**
     * 获取一张专辑的所有评论
     *
     * @param id 专辑id
     * @return 所有相关评论
     */
    @Async("chillPool")
    @SneakyThrows

    CompletableFuture<List<AlbumComment>> getCommentsByAlbumId(Long id);

    /**
     * 删除所有对应专辑的评论
     *
     * @param albumId 专辑id
     */
    @SneakyThrows

    void deleteAllByAlbumId(Long albumId);

    /**
     * 添加评论
     *
     * @param albumComment 评论实体
     * @return 是否添加成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> add(AlbumComment albumComment);

    /**
     * 删除评论
     *
     * @param albumComment 评论实体
     * @return 是否删除成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> delete(AlbumComment albumComment);
}
