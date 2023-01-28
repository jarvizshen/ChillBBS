package com.chill.chillbbs.service.album.impl;

import com.chill.chillbbs.entity.album.AlbumComment;
import com.chill.chillbbs.repository.album.AlbumCommentRepository;
import com.chill.chillbbs.service.album.AlbumCommentService;
import com.chill.chillbbs.util.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jarviz
 */
@Service

public class AlbumCommentServiceImpl implements AlbumCommentService {
    @Resource
    AlbumCommentRepository albumCommentRepository;
    @Resource
    RabbitTemplate rabbitTemplate;


    @Override
    public List<AlbumComment> getCommentsByAlbumId(Long id) {
        return albumCommentRepository.findAllByAlbumId(id);
    }

    @Override
    public void deleteAllByAlbumId(Long albumId) {
        if (albumCommentRepository.findAllByAlbumId(albumId).size() > 0) {
            albumCommentRepository.findAllByAlbumId(albumId).forEach(albumComment -> {
                albumCommentRepository.deleteById(albumComment.getId());
                rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_ALBUM_COMMENT_REPLY_KEY, albumComment.getId());
            });
        }
    }

    @Override
    public Boolean add(AlbumComment albumComment) {
        albumCommentRepository.save(albumComment);
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.INCREASE_ALBUM_COMMENT_NUMBER_KEY, albumComment.getAlbumId());
        return true;
    }

    @Override
    public Boolean delete(AlbumComment albumComment) {
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DECREASE_ALBUM_COMMENT_NUMBER_KEY, albumComment.getAlbumId());
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_ALBUM_COMMENT_REPLY_KEY, albumComment.getId());
        albumCommentRepository.delete(albumComment);
        return true;
    }
}
