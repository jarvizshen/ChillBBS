package com.chill.chillbbs.repository.album;

import com.chill.chillbbs.entity.album.AlbumCommentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jarviz
 */
@Repository

public interface AlbumCommentReplyRepository extends JpaRepository<AlbumCommentReply, Long> {
    /**
     * 获取对应评论的所有回复
     *
     * @param commentId 评论id
     * @return 所有回复
     */
    List<AlbumCommentReply> findAllByCommentId(Long commentId);

    /**
     * 删除所有对应评论的回复
     *
     * @param commentId 评论id
     */
    void deleteAllByCommentId(Long commentId);
}
