package com.chill.chillbbs.service.album.impl;

import com.chill.chillbbs.entity.album.AlbumCommentReply;
import com.chill.chillbbs.repository.album.AlbumCommentReplyRepository;
import com.chill.chillbbs.service.album.AlbumCommentReplyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jarviz
 */
@Service
public class AlbumCommentReplyServiceImpl implements AlbumCommentReplyService {
    @Resource
    AlbumCommentReplyRepository albumCommentReplyRepository;

    @Override
    public List<AlbumCommentReply> getAllByCommentId(Long commentId) {
        return albumCommentReplyRepository.findAllByCommentId(commentId);
    }

    @Override
    public Boolean delete(Long id) {
        albumCommentReplyRepository.deleteById(id);
        return true;
    }

    @Override
    public void deleteAllByCommentId(Long commentId) {
        if (albumCommentReplyRepository.findAllByCommentId(commentId).size() > 0) {
            albumCommentReplyRepository.findAllByCommentId(commentId).forEach(albumCommentReply -> albumCommentReplyRepository.deleteById(albumCommentReply.getId()));
        }
    }
}
