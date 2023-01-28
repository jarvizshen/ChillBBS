package com.chill.chillbbs.service.album.impl;

import com.chill.chillbbs.entity.album.Album;
import com.chill.chillbbs.repository.album.AlbumRepository;
import com.chill.chillbbs.service.album.AlbumService;
import com.chill.chillbbs.util.Constants;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Page<Album> allAlbumsPage(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    @Override
    public List<Album> allAlbums() {
        return albumRepository.findAll(Sort.by(Sort.Direction.ASC, "albumName"));
    }

    @Override
    public List<Album> findAllByAlbumName(String albumName) {
//        Specification<Album> specification = new Specification<Album>() {
//            @Override
//            public Predicate toPredicate(Root<Album> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Path<String> albumName1 = root.get("albumName");
//                Predicate predicate = criteriaBuilder.like(albumName1.type().as(String.class), "%" + albumName1 + "%");
//                return predicate;
//            }
//        };
        return albumRepository.findAllByAlbumNameLike("%" + albumName + "%");
    }

    @Override
    public Boolean deleteAlbumById(long id) {
        try {
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.DELETE_ALBUM_COMMENT_KEY, id);
            albumRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Album saveOrUpdate(Album album) {
        albumRepository.save(album);
        return album;
    }

    @Override
    public Optional<Album> getById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Boolean increaseComment(Long albumId) {
        assert albumRepository.findById(albumId).isPresent();
        Album album = albumRepository.findById(albumId).get();
        album.setCommentNum(album.getCommentNum() + 1);
        System.out.println(album);
        saveOrUpdate(album);
        return true;
    }

    @Override
    public Boolean decreaseComment(Long postId) {
        assert albumRepository.findById(postId).isPresent();
        Album album = albumRepository.findById(postId).get();
        album.setCommentNum(album.getCommentNum() - 1);
        saveOrUpdate(album);
        return true;
    }
}
