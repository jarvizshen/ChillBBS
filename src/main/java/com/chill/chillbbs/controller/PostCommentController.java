package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.post.PostComment;
import com.chill.chillbbs.service.post.PostCommentService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@RestController
@EnableAsync
@RequestMapping("/api/postComment")
public class PostCommentController {
    @Resource
    PostCommentService postCommentService;

    @GetMapping("/all")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Page<PostComment>>> allComments(Long id, @RequestParam(required = false,
            defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postCommentService.getCommentsByPostId(id, PageRequest.of(page, size))));
    }

    @PostMapping("/add")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Boolean>> add(@RequestBody PostComment postComment) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postCommentService.add(postComment)));
    }

    @DeleteMapping("/delete")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Boolean>> delete(@RequestBody PostComment postComment) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postCommentService.delete(postComment)));
    }
}
