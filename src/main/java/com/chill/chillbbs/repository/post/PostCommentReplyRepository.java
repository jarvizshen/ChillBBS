package com.chill.chillbbs.repository.post;

import com.chill.chillbbs.entity.post.PostCommentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jarviz
 */
@Repository

public interface PostCommentReplyRepository extends JpaRepository<PostCommentReply, Long> {
    /**
     * 获取对应评论的所有回复
     *
     * @param commentId 评论id
     * @return 所有回复
     */
    List<PostCommentReply> findAllByCommentId(Long commentId);

    /**
     * 删除所有对应评论的回复
     *
     * @param commentId 评论id
     */
    void deleteAllByCommentId(Long commentId);
}
