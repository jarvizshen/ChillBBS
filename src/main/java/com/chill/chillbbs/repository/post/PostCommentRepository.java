package com.chill.chillbbs.repository.post;

import com.chill.chillbbs.entity.post.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论rep
 *
 * @author Jarviz
 */
@Repository

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    /**
     * 获得帖子的所有评论分页数据
     *
     * @param postId   帖子id
     * @param pageable 分页参数
     * @return 评论分页数据
     */
    Page<PostComment> getCommentByPostIdOrderByIdDesc(Long postId, Pageable pageable);

    /**
     * 删除所有对应话题的评论
     *
     * @param postId 话题id
     */
    @Modifying
    void deleteAllByPostId(Long postId);

    /**
     * 获取所有对应话题的评论
     *
     * @param postId 话题id
     * @return 所有对应评论
     */
    List<PostComment> findAllByPostId(Long postId);
    /**
     * 获取所有对应用户的评论
     *
     * @param userId 用户id
     * @return 所有对应评论
     */
    List<PostComment> findAllByUserId(Long userId);
}
