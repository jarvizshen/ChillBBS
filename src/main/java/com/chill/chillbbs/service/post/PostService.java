package com.chill.chillbbs.service.post;

import com.chill.chillbbs.entity.post.Post;
import com.chill.chillbbs.util.PostOrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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
    List<Post> search(String keyword);
    /**
     * 返回所有帖子
     *
     * @return 所有帖子
     */
    List<Post> allPosts(PostOrderType orderType,PostOrderType ascOrDesc);

    /**
     * 根据帖子id删除
     *
     * @param id id
     * @return 是否删除成功
     */
    boolean deletePostById(long id);

    /**
     * 添加或更新帖子
     *
     * @param postEntity 帖子实体
     * @return 是否添加或更新成功
     */
    Post saveOrUpdatePost(Post postEntity);

    /**
     * 根据id获得对应帖子
     *
     * @param id 帖子id
     * @return 对应帖子
     */
    Optional<Post> getById(Long id);

    /**
     * 评论数量加一
     * @param postId 话题id
     *
     * @return 是否成功
     */
    Boolean increaseComment(Long postId);

    /**
     * 评论数量减一
     *
     * @param postId 话题id
     * @return 是否成功
     */
    Boolean decreaseComment(Long postId);
    /**
     * 改变话题收藏状态
     *
     * @param postId 话题id
     * @param collected 是否收藏
     * @return 是否成功
     */
    Boolean collected(Long postId, Boolean collected);
}
