package com.chill.chillbbs.service.album.impl;

import com.chill.chillbbs.entity.album.AlbumComment;
import com.chill.chillbbs.repository.album.AlbumCommentRepository;
import com.chill.chillbbs.service.album.AlbumCommentService;
import com.chill.chillbbs.service.util.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<List<AlbumComment>> getCommentsByAlbumId(Long id) {
        return CompletableFuture.completedFuture(albumCommentRepository.findAllByAlbumId(id));
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
    public CompletableFuture<Boolean> add(AlbumComment albumComment) {
        try {
            albumCommentRepository.save(albumComment);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.INCREASE_ALBUM_COMMENT_NUMBER_KEY, albumComment.getAlbumId());
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public CompletableFuture<Boolean> delete(AlbumComment albumComment) {
        try {
            albumCommentRepository.delete(albumComment);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DECREASE_ALBUM_COMMENT_NUMBER_KEY, albumComment.getAlbumId());
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_ALBUM_COMMENT_REPLY_KEY, albumComment.getId());
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }
}
