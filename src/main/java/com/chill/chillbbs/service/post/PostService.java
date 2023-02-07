package com.chill.chillbbs.service.post;

import com.chill.chillbbs.entity.post.Post;
import com.chill.chillbbs.service.util.PostOrderType;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * 帖子服务
 *
 * @author Jarviz
 */
public interface PostService {
    /**
     * 模糊检索标题或内容
     *
     * @param keyword 关键字
     * @return 匹配结果
     */
    @Async("chillPool")
    @SneakyThrows

    CompletableFuture<List<Post>> search(String keyword);

    /**
     * 返回所有帖子
     *
     * @param orderType 排序类型
     * @param ascOrDesc 升序或降序
     * @return 所有帖子
     */
    @Async("chillPool")
    @SneakyThrows

    CompletableFuture<List<Post>> allPosts(PostOrderType orderType, PostOrderType ascOrDesc);

    /**
     * 模糊检索标题或内容
     *
     * @param keyword   关键字
     * @param pageable  分页参数
     * @param ascOrDesc 升序或降序
     * @param orderType 排序类型
     * @return 匹配结果
     */
    @SneakyThrows

    CompletableFuture<Page<Post>> searchPage(String keyword, PostOrderType orderType, PostOrderType ascOrDesc, Pageable pageable);

    /**
     * 返回所有帖子分页结果
     *
     * @param orderType 排序类型
     * @param ascOrDesc 升序或降序
     * @param pageable  分页参数
     * @return 所有帖子分页结果
     */
    @Async("chillPool")
    @SneakyThrows

    CompletableFuture<Page<Post>> allPostsPage(PostOrderType orderType, PostOrderType ascOrDesc, Pageable pageable);

    /**
     * 根据帖子id删除
     *
     * @param id id
     * @return 是否删除成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> deletePostById(long id);

    /**
     * 添加或更新帖子
     *
     * @param postEntity 帖子实体
     * @return 是否添加或更新成功
     */
    @Async("chillPool")
    @SneakyThrows

    CompletableFuture<Post> saveOrUpdatePost(Post postEntity);

    /**
     * 根据id获得对应帖子
     *
     * @param id 帖子id
     * @return 对应帖子
     */
    @Async("chillPool")
    @SneakyThrows

    CompletableFuture<Optional<Post>> getById(Long id);

    /**
     * 评论数量加一
     *
     * @param postId 话题id
     */
    @SneakyThrows

    void increaseComment(Long postId);

    /**
     * 评论数量减一
     *
     * @param postId 话题id
     */
    @SneakyThrows

    void decreaseComment(Long postId);
    /**
     * 点赞数量加一
     *
     * @param postId 话题id
     */
    @SneakyThrows

    void increaseLike(Long postId);

    /**
     * 点赞数量减一
     *
     * @param postId 话题id
     */
    @SneakyThrows

    void decreaseLike(Long postId);
    /**
     * 收藏数量加一
     *
     * @param postId 话题id
     */
    @SneakyThrows

    void increaseCollect(Long postId);

    /**
     * 收藏数量减一
     *
     * @param postId 话题id
     */
    @SneakyThrows

    void decreaseCollect(Long postId);
}
