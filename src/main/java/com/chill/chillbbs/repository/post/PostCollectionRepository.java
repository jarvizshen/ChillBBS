package com.chill.chillbbs.repository.post;

import com.chill.chillbbs.entity.album.AlbumCollection;
import com.chill.chillbbs.entity.post.PostCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jarviz
 */
@Repository

public interface PostCollectionRepository extends JpaRepository<PostCollection, Long> {
    /**
     * 获取所有对应专辑的收藏信息
     *
     * @param postId 专辑id
     * @return 所有对应信息
     */
    List<PostCollection> findAllByPostId(Long postId);

    /**
     * 根据话题和用户id查找收藏信息
     *
     * @param postId 话题id
     * @param userId 用户id
     * @return 收藏信息
     */
    Optional<PostCollection> findByPostIdAndUserId(Long postId, Long userId);
}
