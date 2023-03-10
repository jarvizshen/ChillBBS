package com.chill.chillbbs.service.post.impl;

import com.chill.chillbbs.entity.album.AlbumComment;
import com.chill.chillbbs.entity.post.PostComment;
import com.chill.chillbbs.repository.post.PostCommentRepository;
import com.chill.chillbbs.service.post.PostCommentService;
import com.chill.chillbbs.service.util.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service

public class PostCommentServiceImpl implements PostCommentService {
    @Resource
    PostCommentRepository postCommentRepository;
    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public CompletableFuture<Page<PostComment>> getCommentsByPostId(Long id, Pageable pageable) {
        return CompletableFuture.completedFuture(postCommentRepository.getCommentByPostIdOrderByIdDesc(id, pageable));
    }

    @Override
    public CompletableFuture<List<PostComment>> getCommentsByUserId(Long id) {
        return CompletableFuture.completedFuture(postCommentRepository.findAllByUserId(id));
    }

    @Override
    public void deleteAllByPostId(Long postId) {
        List<PostComment> all = postCommentRepository.findAllByPostId(postId);
        if (all.size() > 0) {
            all.forEach(postComment -> {
                postCommentRepository.deleteById(postComment.getId());
                rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_POST_COMMENT_REPLY_KEY, postComment.getId());
            });
        }
    }

    @Override
    public CompletableFuture<Boolean> add(PostComment postComment) {
        try {
            postComment.setCreateTime(new Date());
            postCommentRepository.save(postComment);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.INCREASE_POST_COMMENT_NUMBER_KEY, postComment.getPostId());
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public CompletableFuture<Boolean> delete(PostComment postComment) {
        try {
            postCommentRepository.delete(postComment);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DECREASE_POST_COMMENT_NUMBER_KEY, postComment.getPostId());
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_POST_COMMENT_REPLY_KEY, postComment.getId());
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public CompletableFuture<Optional<PostComment>> getById(Long id) {
        return CompletableFuture.completedFuture(postCommentRepository.findById(id));
    }

    @Override
    public void increaseLike(Long postCommentId) {
        assert postCommentRepository.findById(postCommentId).isPresent();
        PostComment postComment = postCommentRepository.findById(postCommentId).get();
        postComment.setLikeNum(postComment.getLikeNum() + 1);
        add(postComment);
    }

    @Override
    public void decreaseLike(Long postCommentId) {
        assert postCommentRepository.findById(postCommentId).isPresent();
        PostComment postComment = postCommentRepository.findById(postCommentId).get();
        postComment.setLikeNum(postComment.getLikeNum() - 1);
        add(postComment);
    }
}
