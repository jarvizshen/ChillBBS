package com.chill.chillbbs.repository.album;

import com.chill.chillbbs.entity.album.AlbumCollection;
import com.chill.chillbbs.entity.album.AlbumCommentLiked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jarviz
 */
@Repository

public interface AlbumCommentLikedRepository extends JpaRepository<AlbumCommentLiked, Long> {
    /**
     * 获取所有对应专辑评论的喜爱信息
     *
     * @param albumCommentId 专辑评论id
     * @return 所有对应信息
     */
    List<AlbumCommentLiked> findAllByAlbumCommentId(Long albumCommentId);

    /**
     * 根据专辑评论和用户id查找评论喜爱信息
     *
     * @param albumCommentId 专辑id
     * @param userId         用户id
     * @return 评论喜爱信息
     */
    Optional<AlbumCommentLiked> findByAlbumCommentIdAndUserId(Long albumCommentId, Long userId);
}
