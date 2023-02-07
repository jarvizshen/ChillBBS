package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.album.AlbumCommentReply;
import com.chill.chillbbs.entity.post.PostCommentReply;
import com.chill.chillbbs.service.album.AlbumCommentReplyService;
import com.chill.chillbbs.service.post.PostCommentReplyService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/post/reply")
public class PostCommentReplyController {
    @Resource
    PostCommentReplyService postCommentReplyService;

    @GetMapping("/all")
    public ResponseEntity<Object> all(Long id) {
        try {
            return ResponseEntity.ok(postCommentReplyService.getAllByCommentId(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody PostCommentReply postCommentReply) {
        try {
            return ResponseEntity.ok(postCommentReplyService.add(postCommentReply).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(Long id) {
        try {
            return ResponseEntity.ok(postCommentReplyService.delete(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
