package com.chill.chillbbs.service.album;

import com.chill.chillbbs.entity.album.AlbumCommentReply;

import java.util.List;

/**
 * @author Jarviz
 */
public interface AlbumCommentReplyService {
    /**
     * 获取对应评论的所有回复
     *
     * @param commentId 评论id
     * @return 所有回复
     */
    List<AlbumCommentReply> getAllByCommentId(Long commentId);

    /**
     * 删除回复
     *
     * @param id 回复id
     * @return 是否删除成功
     */
    Boolean delete(Long id);

    /**
     * 删除所有对应评论的回复
     *
     * @param commentId 评论id
     */
    void deleteAllByCommentId(Long commentId);
}
