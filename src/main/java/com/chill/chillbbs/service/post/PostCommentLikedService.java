package com.chill.chillbbs.service.post;

import com.chill.chillbbs.entity.post.PostCommentLiked;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
public interface PostCommentLikedService {
    /**
     * 添加或更新
     *
     * @param albumCommentLiked 话题评论喜爱信息
     * @return 话题评论喜爱信息
     */
    @Async("chillPool")
    CompletableFuture<PostCommentLiked> saveOrUpdate(PostCommentLiked albumCommentLiked);

    /**
     * 通过id删除
     *
     * @param id 话题评论喜爱信息id
     */
    @Async("chillPool")
    void delete(Long id);

    /**
     * 删除所有对应话题的评论喜爱信息
     *
     * @param postCommentId 话题评论id
     */
    @Async("chillPool")
    void deleteAll(Long postCommentId);

    /**
     * 查找
     *
     * @param postCommentId 话题评论id
     * @param userId        用户id
     * @return 话题评论喜爱信息
     */
    @Async("chillPool")
    CompletableFuture<Optional<PostCommentLiked>> find(Long postCommentId, Long userId);
}
