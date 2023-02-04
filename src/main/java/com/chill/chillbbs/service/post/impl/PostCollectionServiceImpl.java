package com.chill.chillbbs.service.post.impl;

import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.repository.post.PostCollectionRepository;
import com.chill.chillbbs.service.post.PostCollectionService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service
public class PostCollectionServiceImpl implements PostCollectionService {
    @Resource
    PostCollectionRepository postCollectionRepository;

    @Override
    public CompletableFuture<Boolean> add(PostCollection postCollection) {
        try {
            postCollectionRepository.save(postCollection);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public CompletableFuture<Boolean> remove(Long postId) {
        try {
            postCollectionRepository.deleteById(postId);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }
}
