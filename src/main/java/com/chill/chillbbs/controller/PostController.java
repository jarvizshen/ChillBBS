package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.post.Post;
import com.chill.chillbbs.service.post.PostService;
import com.chill.chillbbs.util.PostOrderType;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/post")
@EnableAsync
public class PostController {
    @Resource
    PostService postService;

    @GetMapping("/getById")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Optional<Post>>> getById(Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.getById(id)));
    }

//    @RequestParam(required = false,
//    defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size

    @GetMapping("/all")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Object>> getAllPosts(@RequestParam(required = false
            , defaultValue = "COMMENT") PostOrderType orderType
            , @RequestParam(required = false
            , defaultValue = "DESC") PostOrderType ascOrDesc) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.allPosts(orderType, ascOrDesc)));
    }

    @GetMapping("/search")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Object>> search(String word) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.search(word)));
    }

    @PostMapping("/add")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Post>> saveOrUpdate(@RequestBody Post post) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.saveOrUpdatePost(post)));
    }

    @DeleteMapping("/delete")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Boolean>> delete(long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.deletePostById(id)));
    }

    @Async("chillPool")
    @PutMapping("/collect")
    public CompletableFuture<ResponseEntity<Boolean>> collect(Long postId, Boolean collected) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(postService.collected(postId, collected)));
    }
}
