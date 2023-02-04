package com.chill.chillbbs.service.album.impl;

import com.chill.chillbbs.entity.album.Album;
import com.chill.chillbbs.repository.album.AlbumRepository;
import com.chill.chillbbs.service.album.AlbumService;
import com.chill.chillbbs.util.Constants;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service

public class AlbumServiceImpl implements AlbumService {
    @Resource
    AlbumRepository albumRepository;
    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public CompletableFuture<Page<Album>> allAlbumsPage(Pageable pageable) {
        return CompletableFuture.completedFuture(albumRepository.findAll(pageable));
    }

    @Override
    public CompletableFuture<List<Album>> allAlbums() {
        return CompletableFuture.completedFuture(albumRepository.findAll(Sort.by(Sort.Direction.ASC, "albumName")));
    }

    @Override
    public CompletableFuture<List<Album>> findAllByAlbumName(String albumName) {
        return CompletableFuture.completedFuture(albumRepository.findAllByAlbumNameLikeOrderByAlbumNameAsc("%" + albumName + "%"));
    }

    @Override
    public CompletableFuture<Page<Album>> findAllByAlbumNamePage(String albumName, Pageable pageable) {
        return CompletableFuture.completedFuture(albumRepository.findAllByAlbumNameLikeOrderByAlbumNameAsc("%" + albumName + "%", pageable));
    }

    @Override
    public CompletableFuture<Boolean> deleteAlbumById(long id) {
        try {
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_ALBUM_COMMENT_KEY, id);
            albumRepository.deleteById(id);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    public CompletableFuture<Album> saveOrUpdate(Album album) {
        albumRepository.save(album);
        return CompletableFuture.completedFuture(album);
    }

    @Override
    public CompletableFuture<Optional<Album>> getById(Long id) {
        return CompletableFuture.completedFuture(albumRepository.findById(id));
    }

    @Override
    public void increaseComment(Long albumId) {
        assert albumRepository.findById(albumId).isPresent();
        Album album = albumRepository.findById(albumId).get();
        album.setCommentNum(album.getCommentNum() + 1);
        System.out.println(album);
        saveOrUpdate(album);
    }

    @Override
    public void decreaseComment(Long postId) {
        assert albumRepository.findById(postId).isPresent();
        Album album = albumRepository.findById(postId).get();
        album.setCommentNum(album.getCommentNum() - 1);
        saveOrUpdate(album);
    }
}
