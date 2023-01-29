package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.album.AlbumComment;
import com.chill.chillbbs.service.album.AlbumCommentService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/albumComment")
public class AlbumCommentController {
    @Resource
    AlbumCommentService albumCommentService;

    @GetMapping("/all")
    public ResponseEntity<Object> allCommentsPage(Long id) {
        try {
            return ResponseEntity.ok(albumCommentService.getCommentsByAlbumId(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody AlbumComment albumComment) {
        try {
            return ResponseEntity.ok(albumCommentService.add(albumComment).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody AlbumComment albumComment) {
        try {
            return ResponseEntity.ok(albumCommentService.delete(albumComment).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
