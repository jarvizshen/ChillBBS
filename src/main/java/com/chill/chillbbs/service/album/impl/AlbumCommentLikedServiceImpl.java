package com.chill.chillbbs.service.album.impl;

import com.chill.chillbbs.entity.album.AlbumCommentLiked;
import com.chill.chillbbs.repository.album.AlbumCommentLikedRepository;
import com.chill.chillbbs.service.album.AlbumCommentLikedService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service
public class AlbumCommentLikedServiceImpl implements AlbumCommentLikedService {
    @Resource
    AlbumCommentLikedRepository albumCommentLikedRepository;

    @Override
    public CompletableFuture<AlbumCommentLiked> saveOrUpdate(AlbumCommentLiked albumCommentLiked) {
        return CompletableFuture.completedFuture(albumCommentLikedRepository.save(albumCommentLiked));
    }

    @Override
    public void delete(Long id) {
        albumCommentLikedRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Long albumCommentId) {
        List<AlbumCommentLiked> all = albumCommentLikedRepository.findAllByAlbumCommentId(albumCommentId);
        if (all.size() > 0) {
            all.forEach(albumCommentLiked -> delete(albumCommentLiked.getId()));
        }
    }

    @Override
    public CompletableFuture<Optional<AlbumCommentLiked>> find(Long albumCommentId, Long userId) {
        return CompletableFuture.completedFuture(albumCommentLikedRepository.findByAlbumCommentIdAndUserId(albumCommentId, userId));
    }
}
