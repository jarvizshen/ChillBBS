package com.chill.chillbbs.service.post;

import com.chill.chillbbs.entity.post.PostCollection;

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
    Boolean add(PostCollection postCollection);
    /**
     * 取消收藏话题
     *
     * @param postId 话题id
     * @return 是否取消收藏成功
     */
    Boolean remove(Long postId);

}
