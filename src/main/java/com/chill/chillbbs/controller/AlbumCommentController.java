package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.album.AlbumComment;
import com.chill.chillbbs.entity.post.PostComment;
import com.chill.chillbbs.service.album.AlbumCommentService;
import com.chill.chillbbs.service.post.PostCommentService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@RestController
@EnableAsync
@RequestMapping("/api/albumComment")
public class AlbumCommentController {
    @Resource
    AlbumCommentService albumCommentService;

    @GetMapping("/all")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<List<AlbumComment>>> allCommentsPage(Long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(albumCommentService.getCommentsByAlbumId(id)));
    }

    @PostMapping("/add")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Boolean>> add(@RequestBody AlbumComment albumComment) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(albumCommentService.add(albumComment)));
    }

    @DeleteMapping("/delete")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Boolean>> delete(@RequestBody AlbumComment albumComment) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(albumCommentService.delete(albumComment)));
    }
}
