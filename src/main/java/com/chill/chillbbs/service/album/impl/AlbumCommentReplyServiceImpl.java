package com.chill.chillbbs.service.album.impl;

import com.chill.chillbbs.entity.album.AlbumCommentReply;
import com.chill.chillbbs.repository.album.AlbumCommentReplyRepository;
import com.chill.chillbbs.service.album.AlbumCommentReplyService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service
public class AlbumCommentReplyServiceImpl implements AlbumCommentReplyService {
    @Resource
    AlbumCommentReplyRepository albumCommentReplyRepository;

    @Override
    public CompletableFuture<List<AlbumCommentReply>> getAllByCommentId(Long commentId) {
        return CompletableFuture.completedFuture(albumCommentReplyRepository.findAllByCommentId(commentId));
    }

    @Override
    public CompletableFuture<Boolean> delete(Long id) {
        try {
            albumCommentReplyRepository.deleteById(id);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public CompletableFuture<AlbumCommentReply> add(AlbumCommentReply albumCommentReply) {
        return CompletableFuture.completedFuture(albumCommentReplyRepository.save(albumCommentReply));
    }

    @Override
    public void deleteAllByCommentId(Long commentId) {
        if (albumCommentReplyRepository.findAllByCommentId(commentId).size() > 0) {
            albumCommentReplyRepository.findAllByCommentId(commentId).forEach(albumCommentReply -> albumCommentReplyRepository.deleteById(albumCommentReply.getId()));
        }
    }
}
