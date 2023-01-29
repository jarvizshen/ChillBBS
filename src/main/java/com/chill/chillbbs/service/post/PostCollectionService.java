package com.chill.chillbbs.service.post;

import com.chill.chillbbs.entity.post.PostCollection;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
public interface PostCollectionService {
    /**
     * 收藏话题
     *
     * @param postCollection 话题收藏信息
     * @return 是否收藏成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> add(PostCollection postCollection);
    /**
     * 取消收藏话题
     *
     * @param postId 话题id
     * @return 是否取消收藏成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> remove(Long postId);

}
