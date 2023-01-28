package com.chill.chillbbs.service.post.impl;

import com.chill.chillbbs.entity.post.PostCommentReply;
import com.chill.chillbbs.repository.post.PostCommentReplyRepository;
import com.chill.chillbbs.service.post.PostCommentReplyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jarviz
 */
@Service
public class PostCommentReplyServiceImpl implements PostCommentReplyService {
    @Resource
    PostCommentReplyRepository postCommentReplyRepository;

    @Override
    public List<PostCommentReply> getAllByCommentId(Long commentId) {
        return postCommentReplyRepository.findAllByCommentId(commentId);
    }

    @Override
    public Boolean delete(Long id) {
        postCommentReplyRepository.deleteById(id);
        return true;
    }

    @Override
    public void deleteAllByCommentId(Long commentId) {
        if (postCommentReplyRepository.findAllByCommentId(commentId).size() > 0) {
            postCommentReplyRepository.findAllByCommentId(commentId).forEach(postCommentReply -> postCommentReplyRepository.deleteById(postCommentReply.getId()));
        }
    }
}
