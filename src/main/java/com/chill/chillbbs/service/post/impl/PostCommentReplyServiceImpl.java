package com.chill.chillbbs.service.post.impl;

import com.chill.chillbbs.entity.post.PostCommentReply;
import com.chill.chillbbs.repository.post.PostCommentReplyRepository;
import com.chill.chillbbs.service.post.PostCommentReplyService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service
public class PostCommentReplyServiceImpl implements PostCommentReplyService {
    @Resource
    PostCommentReplyRepository postCommentReplyRepository;

    @Override
    public CompletableFuture<List<PostCommentReply>> getAllByCommentId(Long commentId) {
        return CompletableFuture.completedFuture(postCommentReplyRepository.findAllByCommentId(commentId));
    }

    @Override
    public CompletableFuture<Boolean> delete(Long id) {
        try {
            postCommentReplyRepository.deleteById(id);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public CompletableFuture<PostCommentReply> add(PostCommentReply postCommentReply) {
        return CompletableFuture.completedFuture(postCommentReplyRepository.save(postCommentReply));
    }

    @Override
    public void deleteAllByCommentId(Long commentId) {
        List<PostCommentReply> all = postCommentReplyRepository.findAllByCommentId(commentId);
        if (all.size() > 0) {
            all.forEach(postCommentReply -> postCommentReplyRepository.deleteById(postCommentReply.getId()));
        }
    }
}
