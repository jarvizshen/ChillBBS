package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.album.AlbumCollection;
import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.service.album.AlbumCollectionService;
import com.chill.chillbbs.service.post.PostCollectionService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/album/collection")
public class AlbumCollectionController {
    @Resource
    AlbumCollectionService albumCollectionService;

    @PostMapping("/add")
    public ResponseEntity<Object> collect(@RequestBody AlbumCollection albumCollection) {
        return ResponseEntity.ok(albumCollectionService.saveOrUpdate(albumCollection));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> cancelCollect(@RequestBody AlbumCollection albumCollection) {
        try {
            albumCollectionService.delete(albumCollection);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> get(Long albumId, Long userId) {
        return ResponseEntity.ok(albumCollectionService.find(albumId, userId));
    }
}
