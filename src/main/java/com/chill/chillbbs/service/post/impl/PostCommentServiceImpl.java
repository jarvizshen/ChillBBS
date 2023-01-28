package com.chill.chillbbs.service.post.impl;

import com.chill.chillbbs.entity.post.PostComment;
import com.chill.chillbbs.repository.post.PostCommentRepository;
import com.chill.chillbbs.service.post.PostCommentService;
import com.chill.chillbbs.util.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<PostComment> getCommentsByPostId(Long id, Pageable pageable) {
        return postCommentRepository.getCommentByPostIdOrderByIdDesc(id, pageable);
    }

    @Override
    public void deleteAllByPostId(Long postId) {
        if (postCommentRepository.findAllByPostId(postId).size() > 0) {
            postCommentRepository.findAllByPostId(postId).forEach(postComment -> {
                postCommentRepository.deleteById(postComment.getId());
                rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_POST_COMMENT_REPLY_KEY, postComment.getId());
            });
        }
    }

    @Override
    public Boolean add(PostComment postComment) {
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.INCREASE_POST_DOC_COMMENT_NUMBER_KEY, postComment.getPostId());
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.INCREASE_POST_COMMENT_NUMBER_KEY, postComment.getPostId());
        postCommentRepository.save(postComment);
        return true;
    }

    @Override
    public Boolean delete(PostComment postComment) {
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DECREASE_POST_DOC_COMMENT_NUMBER_KEY, postComment.getPostId());
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DECREASE_POST_COMMENT_NUMBER_KEY, postComment.getPostId());
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_POST_COMMENT_REPLY_KEY, postComment.getId());
        postCommentRepository.delete(postComment);
        return true;
    }
}
