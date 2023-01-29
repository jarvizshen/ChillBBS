package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.post.Post;
import com.chill.chillbbs.service.post.PostService;
import com.chill.chillbbs.util.PostOrderType;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Resource
    PostService postService;

    @GetMapping("/getById")
    public ResponseEntity<Object> getById(Long id) {
        try {
            return ResponseEntity.ok(postService.getById(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

//    @RequestParam(required = false,
//    defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size

    @GetMapping("/all")
    public ResponseEntity<Object> getAllPosts(@RequestParam(required = false
            , defaultValue = "COMMENT") PostOrderType orderType
            , @RequestParam(required = false
            , defaultValue = "DESC") PostOrderType ascOrDesc) {
        try {
            return ResponseEntity.ok(postService.allPosts(orderType, ascOrDesc).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(String word) {
        try {
            return ResponseEntity.ok(postService.search(word).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> saveOrUpdate(@RequestBody Post post) {
        try {
            return ResponseEntity.ok(postService.saveOrUpdatePost(post).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(long id) {
        try {
            return ResponseEntity.ok(postService.deletePostById(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/collect")
    public ResponseEntity<Object> collect(Long postId, Boolean collected) {
        try {
            return ResponseEntity.ok(postService.collected(postId, collected).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
