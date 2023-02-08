package com.chill.chillbbs.service.album.impl;

import com.chill.chillbbs.entity.album.AlbumCollection;
import com.chill.chillbbs.repository.album.AlbumCollectionRepository;
import com.chill.chillbbs.service.album.AlbumCollectionService;
import com.chill.chillbbs.service.util.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@Service
public class AlbumCollectionServiceImpl implements AlbumCollectionService {
    @Resource
    AlbumCollectionRepository albumCollectionRepository;
    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public CompletableFuture<AlbumCollection> saveOrUpdate(AlbumCollection albumCollection) {
        try {
            AlbumCollection save = albumCollectionRepository.save(albumCollection);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.INCREASE_ALBUM_COLLECT_NUMBER_KEY, albumCollection.getAlbumId());
            return CompletableFuture.completedFuture(save);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(AlbumCollection albumCollection) {
        try {
            albumCollectionRepository.delete(albumCollection);
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DECREASE_ALBUM_COLLECT_NUMBER_KEY, albumCollection.getAlbumId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(Long albumId) {
        List<AlbumCollection> all = albumCollectionRepository.findAllByAlbumId(albumId);
        if (all.size() > 0) {
            all.forEach(albumComment -> albumCollectionRepository.deleteById(albumComment.getId()));
        }
    }

    @Override
    public CompletableFuture<Optional<AlbumCollection>> find(Long albumId, Long userId) {
        return CompletableFuture.completedFuture(albumCollectionRepository.findByAlbumIdAndUserId(albumId, userId));
    }
}
