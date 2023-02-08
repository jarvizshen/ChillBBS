package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.service.post.PostCollectionService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/post/collection")
public class PostCollectionController {
    @Resource
    PostCollectionService postCollectionService;

    @PostMapping("/add")
    public ResponseEntity<Object> collect(@RequestBody PostCollection postCollection) {
        try {
            return ResponseEntity.ok(postCollectionService.saveOrUpdate(postCollection).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> cancelCollect(@RequestBody PostCollection postCollection) {
        try {
            postCollectionService.delete(postCollection);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> get(Long postId,Long userId) {
        try {
            return ResponseEntity.ok(postCollectionService.find(postId,userId).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
