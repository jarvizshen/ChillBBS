package com.chill.chillbbs.service.post;

import com.chill.chillbbs.entity.album.AlbumCommentReply;
import com.chill.chillbbs.entity.post.PostCommentReply;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
public interface PostCommentReplyService {
    /**
     * 获取对应评论的所有回复
     *
     * @param commentId 评论id
     * @return 所有回复
     */
    @Async("chillPool")
    @SneakyThrows
    CompletableFuture<List<PostCommentReply>> getAllByCommentId(Long commentId);

    /**
     * 删除回复
     *
     * @param id 回复id
     * @return 是否删除成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> delete(Long id);

    /**
     * 添加回复
     *
     * @param postCommentReply 回复
     * @return 回复
     */
    @Async("chillPool")
    CompletableFuture<PostCommentReply> add(PostCommentReply postCommentReply);

    /**
     * 删除所有对应评论的回复
     *
     * @param commentId 评论id
     */
    @SneakyThrows
    void deleteAllByCommentId(Long commentId);

}
