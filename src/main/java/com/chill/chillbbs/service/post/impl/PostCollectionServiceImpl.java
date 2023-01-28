package com.chill.chillbbs.service.post.impl;

import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.repository.post.PostCollectionRepository;
import com.chill.chillbbs.service.post.PostCollectionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Jarviz
 */
@Service
public class PostCollectionServiceImpl implements PostCollectionService {
    @Resource
    PostCollectionRepository postCollectionRepository;

    @Override
    public Boolean add(PostCollection postCollection) {
        postCollectionRepository.save(postCollection);
        return true;
    }

    @Override
    public Boolean remove(Long postId) {
        postCollectionRepository.deleteById(postId);
        return true;
    }
}
