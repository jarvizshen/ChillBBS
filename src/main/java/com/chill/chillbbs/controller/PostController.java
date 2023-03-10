package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.post.Post;
import com.chill.chillbbs.entity.post.PostCollection;
import com.chill.chillbbs.service.post.PostCollectionService;
import com.chill.chillbbs.service.post.PostLikedService;
import com.chill.chillbbs.service.post.PostService;
import com.chill.chillbbs.service.util.PostOrderType;
import jakarta.annotation.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Resource
    PostService postService;

    @Resource
    PostLikedService postLikedService;

    @GetMapping("/getById")
    public ResponseEntity<Object> getById(Long id) {
        try {
            return ResponseEntity.ok(postService.getById(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/allPage")
    public ResponseEntity<Object> getAllPostsPage(@RequestParam(required = false
            , defaultValue = "CREATE") PostOrderType orderType
            , @RequestParam(required = false
            , defaultValue = "DESC") PostOrderType ascOrDesc, @RequestParam(required = false,
            defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size) {
        try {
            return ResponseEntity.ok(postService.allPostsPage(orderType, ascOrDesc, PageRequest.of(page, size)).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

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

    @GetMapping("/searchPage")
    public ResponseEntity<Object> searchPage(String word, @RequestParam(required = false
            , defaultValue = "CREATE") PostOrderType orderType
            , @RequestParam(required = false
            , defaultValue = "DESC") PostOrderType ascOrDesc, @RequestParam(required = false,
            defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size) {
        try {
            return ResponseEntity.ok(postService.searchPage(word, orderType, ascOrDesc, PageRequest.of(page, size)).get());
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
}
