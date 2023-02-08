package com.chill.chillbbs.service.post.impl;

import com.chill.chillbbs.entity.post.PostCommentLiked;
import com.chill.chillbbs.repository.post.PostCommentLikedRepository;
import com.chill.chillbbs.service.post.PostCommentLikedService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service
public class PostCommentLikedServiceImpl implements PostCommentLikedService {
    @Resource
    PostCommentLikedRepository postCommentLikedRepository;

    @Override
    public CompletableFuture<PostCommentLiked> saveOrUpdate(PostCommentLiked postCommentLiked) {
        return CompletableFuture.completedFuture(postCommentLikedRepository.save(postCommentLiked));
    }

    @Override
    public void delete(Long id) {
        postCommentLikedRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Long postCommentId) {
        List<PostCommentLiked> all = postCommentLikedRepository.findAllByPostCommentId(postCommentId);
        if (all.size() > 0) {
            all.forEach(postCommentLiked -> delete(postCommentLiked.getId()));
        }
    }

    @Override
    public CompletableFuture<Optional<PostCommentLiked>> find(Long postCommentId, Long userId) {
        return CompletableFuture.completedFuture(postCommentLikedRepository.findByPostCommentIdAndUserId(postCommentId, userId));
    }
}
