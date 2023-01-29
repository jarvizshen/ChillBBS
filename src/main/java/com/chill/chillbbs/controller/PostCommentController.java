package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.post.PostComment;
import com.chill.chillbbs.service.post.PostCommentService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/postComment")
public class PostCommentController {
    @Resource
    PostCommentService postCommentService;

    @GetMapping("/all")
    public ResponseEntity<Object> allComments(Long id, @RequestParam(required = false,
            defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size) {
        try {
            return ResponseEntity.ok(postCommentService.getCommentsByPostId(id, PageRequest.of(page, size)).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody PostComment postComment) {
        try {
            return ResponseEntity.ok(postCommentService.add(postComment).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody PostComment postComment) {
        try {
            return ResponseEntity.ok(postCommentService.delete(postComment).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
