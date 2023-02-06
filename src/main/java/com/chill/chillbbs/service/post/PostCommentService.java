package com.chill.chillbbs.service.post;

import com.chill.chillbbs.entity.post.PostComment;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * 评论服务
 *
 * @author Jarviz
 */
public interface PostCommentService {
    /**
     * 获取一条帖子的所有评论
     *
     * @param id       帖子id
     * @param pageable 分页参数
     * @return 所有相关评论
     */
    @Async("chillPool")
    @SneakyThrows
    CompletableFuture<Page<PostComment>> getCommentsByPostId(Long id, Pageable pageable);

    /**
     * 获取一名用户的所有评论
     *
     * @param id 用户id
     * @return 所有相关评论
     */
    @Async("chillPool")
    @SneakyThrows
    CompletableFuture<List<PostComment>> getCommentsByUserId(Long id);

    /**
     * 删除所有对应话题的评论
     *
     * @param postId 话题id
     */
    @SneakyThrows
    void deleteAllByPostId(Long postId);

    /**
     * 添加评论
     *
     * @param postComment 评论实体
     * @return 是否添加成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> add(PostComment postComment);

    /**
     * 删除评论
     *
     * @param postComment 评论实体
     * @return 是否删除成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> delete(PostComment postComment);
    /**
     * 获取一条评论
     *
     * @param id 评论id
     * @return 评论
     */
    @Async("chillPool")
    CompletableFuture<Optional<PostComment>> getById(Long id);
}
