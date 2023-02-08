package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.entity.post.PostLiked;
import com.chill.chillbbs.service.post.PostCollectionService;
import com.chill.chillbbs.service.post.PostLikedService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/post/liked")
public class PostLikedController {
    @Resource
    PostLikedService postLikedService;

    @PostMapping("/add")
    public ResponseEntity<Object> collect(@RequestBody PostLiked postLiked) {
        try {
            return ResponseEntity.ok(postLikedService.saveOrUpdate(postLiked).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> cancelCollect(@RequestBody PostLiked postLiked) {
        try {
            postLikedService.delete(postLiked);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> get(Long postId,Long userId) {
        try {
            return ResponseEntity.ok(postLikedService.find(postId,userId).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
