package com.chill.chillbbs.repository.post;

import com.chill.chillbbs.entity.album.AlbumCommentLiked;
import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.entity.post.PostCommentLiked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jarviz
 */
@Repository

public interface PostCommentLikedRepository extends JpaRepository<PostCommentLiked, Long> {
    /**
     * 获取所有对应话题评论的喜爱信息
     *
     * @param postCommentId 话题评论id
     * @return 所有对应信息
     */
    List<PostCommentLiked> findAllByPostCommentId(Long postCommentId);

    /**
     * 根据话题评论和用户id查找喜爱信息
     *
     * @param postCommentId 话题id
     * @param userId        用户id
     * @return 喜爱信息
     */
    Optional<PostCommentLiked> findByPostCommentIdAndUserId(Long postCommentId, Long userId);

}
