package com.chill.chillbbs.service.post;

import com.chill.chillbbs.entity.post.PostCollection;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
public interface PostCollectionService {
    /**
     * 添加或更新
     *
     * @param postCollection 话题收藏
     * @return 话题收藏
     */
    @Async("chillPool")
    CompletableFuture<PostCollection> saveOrUpdate(PostCollection postCollection);

    /**
     * 通过id删除
     *
     * @param postCollection 话题收藏
     */
    @Async("chillPool")
    void delete(PostCollection postCollection);

    /**
     * 删除所有对应话题的收藏信息
     *
     * @param postId 话题id
     */
    @Async("chillPool")
    void deleteAll(Long postId);

    /**
     * 按id查找
     *
     * @param postId 话题id
     * @param userId 用户id
     * @return 话题收藏信息
     */
    @Async("chillPool")
    CompletableFuture<Optional<PostCollection>> find(Long postId, Long userId);

}
