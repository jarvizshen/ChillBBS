package com.chill.chillbbs.service.post;

import com.chill.chillbbs.entity.post.PostLiked;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
public interface PostLikedService {
    /**
     * 添加或更新
     *
     * @param postLiked 喜爱话题
     * @return 喜爱话题
     */
    @Async("chillPool")
    CompletableFuture<PostLiked> saveOrUpdate(PostLiked postLiked);

    /**
     * 删除
     *
     * @param postLiked 喜爱话题
     */
    @Async("chillPool")
    void delete(PostLiked postLiked);

    /**
     * 删除所有对应话题的喜爱信息
     *
     * @param postId 话题id
     */
    @Async("chillPool")
    void deleteAll(Long postId);

    /**
     * 按id查找
     *
     * @param id id
     * @return 喜爱话题
     */
    @Async("chillPool")
    CompletableFuture<Optional<PostLiked>> find(Long id);
}
