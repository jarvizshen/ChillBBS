package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.album.AlbumCommentLiked;
import com.chill.chillbbs.service.album.AlbumCommentLikedService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/album/comment/liked")
public class AlbumCommentLikedController {
    @Resource
    AlbumCommentLikedService albumCommentLikedService;

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody AlbumCommentLiked albumCommentLiked) {
        try {
            return ResponseEntity.ok(albumCommentLikedService.saveOrUpdate(albumCommentLiked).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(Long id) {
        try {
            albumCommentLikedService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> get(Long albumCommentId, Long userId) {
        try {
            return ResponseEntity.ok(albumCommentLikedService.find(albumCommentId, userId).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
