package com.chill.chillbbs.service.post.impl;

import com.alibaba.fastjson2.JSONObject;
import com.chill.chillbbs.entity.post.Post;
import com.chill.chillbbs.repository.post.PostRepository;
import com.chill.chillbbs.service.post.PostService;
import com.chill.chillbbs.util.Constants;
import com.chill.chillbbs.util.PostOrderType;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Post> search(String keyword) {
        return postRepository.findAllByTitleOrContentLike("%" + keyword + "%");
    }

    @Override
    public List<Post> allPosts(PostOrderType orderType, PostOrderType ascOrDesc) {
        if (ascOrDesc.equals(PostOrderType.DESC)) {
            if (orderType.equals(PostOrderType.CREATE)) {
                return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            } else {
                return postRepository.findAll(Sort.by(Sort.Direction.DESC, "commentNum"));
            }
        } else {
            if (orderType.equals(PostOrderType.CREATE)) {
                return postRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            } else {
                return postRepository.findAll(Sort.by(Sort.Direction.ASC, "commentNum"));
            }
        }
    }


    @Override
    public boolean deletePostById(long id) {
        try {
            postRepository.deleteById(id);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_POST_COMMENT_KEY, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Post saveOrUpdatePost(Post post) {
        post.setCreateTime(new Date());
        postRepository.save(post);
        return post;
    }

    @Override
    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Boolean increaseComment(Long postId) {
        assert postRepository.findById(postId).isPresent();
        Post post = postRepository.findById(postId).get();
        post.setCommentNum(post.getCommentNum() + 1);
        saveOrUpdatePost(post);
        return true;
    }

    @Override
    public Boolean decreaseComment(Long postId) {
        assert postRepository.findById(postId).isPresent();
        Post post = postRepository.findById(postId).get();
        post.setCommentNum(post.getCommentNum() - 1);
        saveOrUpdatePost(post);
        return true;
    }

    @Override
    public Boolean collected(Long postId, Boolean collected) {
        assert postRepository.findById(postId).isPresent();
        Post post = postRepository.findById(postId).get();
        post.setCollected(collected ? 1 : 0);
        post.setCollectNum(post.getCollectNum() + (collected ? 1 : -1));
        saveOrUpdatePost(post);
        Map<String, Object> collectData = new HashMap<>();
        collectData.put("postId", postId);
        collectData.put("collected", collected);
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.POST_DOC_COLLECT_KEY, JSONObject.toJSONString(collectData));
        return true;
    }

}
