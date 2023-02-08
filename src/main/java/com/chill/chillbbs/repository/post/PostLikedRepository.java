package com.chill.chillbbs.repository.post;

import com.chill.chillbbs.entity.album.AlbumCollection;
import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.entity.post.PostCommentLiked;
import com.chill.chillbbs.entity.post.PostLiked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jarviz
 */
@Repository

public interface PostLikedRepository extends JpaRepository<PostLiked, Long> {
    /**
     * 获取所有对应专辑的喜爱信息
     *
     * @param postId 专辑id
     * @return 所有对应信息
     */
    List<PostLiked> findAllByPostId(Long postId);

    /**
     * 根据话题和用户id查找喜爱信息
     *
     * @param postId 话题id
     * @param userId 用户id
     * @return 喜爱信息
     */
    Optional<PostLiked> findByPostIdAndUserId(Long postId, Long userId);

}
