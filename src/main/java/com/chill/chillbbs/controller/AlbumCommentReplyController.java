package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.album.AlbumComment;
import com.chill.chillbbs.entity.album.AlbumCommentReply;
import com.chill.chillbbs.service.album.AlbumCommentReplyService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/album/reply")
public class AlbumCommentReplyController {
    @Resource
    AlbumCommentReplyService albumCommentReplyService;

    @GetMapping("/all")
    public ResponseEntity<Object> all(Long id) {
        try {
            return ResponseEntity.ok(albumCommentReplyService.getAllByCommentId(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody AlbumCommentReply albumCommentReply) {
        try {
            return ResponseEntity.ok(albumCommentReplyService.add(albumCommentReply).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(Long id) {
        try {
            return ResponseEntity.ok(albumCommentReplyService.delete(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
