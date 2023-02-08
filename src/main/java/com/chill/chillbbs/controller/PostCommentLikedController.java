package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.post.PostCommentLiked;
import com.chill.chillbbs.service.post.PostCommentLikedService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/post/comment/liked")
public class PostCommentLikedController {
    @Resource
    PostCommentLikedService postCommentLikedService;

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody PostCommentLiked postCommentLiked) {
        try {
            return ResponseEntity.ok(postCommentLikedService.saveOrUpdate(postCommentLiked).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(Long id) {
        try {
            postCommentLikedService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> get(Long postCommentId, Long userId) {
        try {
            return ResponseEntity.ok(postCommentLikedService.find(postCommentId, userId).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
