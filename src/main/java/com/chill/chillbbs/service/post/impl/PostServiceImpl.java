package com.chill.chillbbs.service.post.impl;

import com.alibaba.fastjson2.JSONObject;
import com.chill.chillbbs.entity.post.Post;
import com.chill.chillbbs.repository.post.PostRepository;
import com.chill.chillbbs.service.post.PostService;
import com.chill.chillbbs.util.Constants;
import com.chill.chillbbs.util.PostOrderType;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service

public class PostServiceImpl implements PostService {
    @Resource
    PostRepository postRepository;
    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public CompletableFuture<List<Post>> search(String keyword) {
        return CompletableFuture.completedFuture(postRepository.findAllByTitleOrContentLike("%" + keyword + "%"));
    }

    @Override
    public CompletableFuture<List<Post>> allPosts(PostOrderType orderType, PostOrderType ascOrDesc) {
        if (ascOrDesc.equals(PostOrderType.DESC)) {
            if (orderType.equals(PostOrderType.CREATE)) {
                return CompletableFuture.completedFuture(postRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
            } else {
                return CompletableFuture.completedFuture(postRepository.findAll(Sort.by(Sort.Direction.DESC, "commentNum")));
            }
        } else {
            if (orderType.equals(PostOrderType.CREATE)) {
                return CompletableFuture.completedFuture(postRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
            } else {
                return CompletableFuture.completedFuture(postRepository.findAll(Sort.by(Sort.Direction.ASC, "commentNum")));
            }
        }
    }


    @Override
    public CompletableFuture<Boolean> deletePostById(long id) {
        try {
            postRepository.deleteById(id);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_POST_COMMENT_KEY, id);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public CompletableFuture<Post> saveOrUpdatePost(Post post) {
        post.setCreateTime(new Date());
        postRepository.save(post);
        return CompletableFuture.completedFuture(post);
    }

    @Override
    public CompletableFuture<Optional<Post>> getById(Long id) {
        return CompletableFuture.completedFuture(postRepository.findById(id));
    }

    @Override
    public void increaseComment(Long postId) {
        assert postRepository.findById(postId).isPresent();
        Post post = postRepository.findById(postId).get();
        post.setCommentNum(post.getCommentNum() + 1);
        saveOrUpdatePost(post);
    }

    @Override
    public void decreaseComment(Long postId) {
        assert postRepository.findById(postId).isPresent();
        Post post = postRepository.findById(postId).get();
        post.setCommentNum(post.getCommentNum() - 1);
        saveOrUpdatePost(post);
    }

    @Override
    public CompletableFuture<Boolean> collected(Long postId, Boolean collected) {
        assert postRepository.findById(postId).isPresent();
        Post post = postRepository.findById(postId).get();
        post.setCollected(collected ? 1 : 0);
        post.setCollectNum(post.getCollectNum() + (collected ? 1 : -1));
        saveOrUpdatePost(post);
        Map<String, Object> collectData = new HashMap<>(2);
        collectData.put("postId", postId);
        collectData.put("collected", collected);
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.POST_DOC_COLLECT_KEY, JSONObject.toJSONString(collectData));
        return CompletableFuture.completedFuture(true);
    }

}
