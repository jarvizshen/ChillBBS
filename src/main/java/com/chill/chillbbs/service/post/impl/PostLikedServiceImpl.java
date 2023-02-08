package com.chill.chillbbs.service.post.impl;

import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.entity.post.PostLiked;
import com.chill.chillbbs.repository.post.PostLikedRepository;
import com.chill.chillbbs.service.post.PostLikedService;
import com.chill.chillbbs.service.util.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service
public class PostLikedServiceImpl implements PostLikedService {
    @Resource
    PostLikedRepository postLikedRepository;
    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public CompletableFuture<PostLiked> saveOrUpdate(PostLiked postLiked) {
        return CompletableFuture.completedFuture(postLikedRepository.save(postLiked));
    }

    @Override
    public void delete(PostLiked postLiked) {
        try {
            postLikedRepository.delete(postLiked);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DECREASE_POST_LIKE_NUMBER_KEY, postLiked.getPostId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(Long postId) {
        List<PostLiked> all = postLikedRepository.findAllByPostId(postId);
        if (all.size() > 0) {
            all.forEach(albumComment -> postLikedRepository.deleteById(albumComment.getId()));
        }
    }

    @Override
    public CompletableFuture<Optional<PostLiked>> find(Long postId, Long userId) {
        return CompletableFuture.completedFuture(postLikedRepository.findByPostIdAndUserId(postId, userId));
    }
}
