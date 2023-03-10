package com.chill.chillbbs.service.post.impl;

import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.repository.post.PostCollectionRepository;
import com.chill.chillbbs.service.post.PostCollectionService;
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
public class PostCollectionServiceImpl implements PostCollectionService {
    @Resource
    PostCollectionRepository postCollectionRepository;
    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public CompletableFuture<PostCollection> saveOrUpdate(PostCollection postCollection) {
        return CompletableFuture.completedFuture(postCollectionRepository.save(postCollection));
    }

    @Override
    public void delete(PostCollection postCollection) {
        try {
            postCollectionRepository.delete(postCollection);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DECREASE_POST_COLLECT_NUMBER_KEY, postCollection.getPostId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(Long postId) {
        List<PostCollection> all = postCollectionRepository.findAllByPostId(postId);
        if (all.size() > 0) {
            all.forEach(albumComment -> postCollectionRepository.deleteById(albumComment.getId()));
        }
    }

    @Override
    public CompletableFuture<Optional<PostCollection>> find(Long postId, Long userId) {
        return CompletableFuture.completedFuture(postCollectionRepository.findByPostIdAndUserId(postId, userId));
    }
}
