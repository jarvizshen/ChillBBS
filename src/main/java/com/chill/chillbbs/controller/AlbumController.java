package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.album.Album;
import com.chill.chillbbs.entity.post.Post;
import com.chill.chillbbs.service.album.AlbumService;
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
@RequestMapping("/api/album")
@EnableAsync
public class AlbumController {
    @Resource
    AlbumService albumService;

    @Async("chillPool")
    @GetMapping("/allPage")
    public CompletableFuture<ResponseEntity<Page<Album>>> getAllAlbumsPage(@RequestParam(required = false,
            defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(albumService.allAlbumsPage(PageRequest.of(page, size))));
    }

    @Async("chillPool")
    @GetMapping("/all")
    public CompletableFuture<ResponseEntity<List<Album>>> getAllAlbums() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(albumService.allAlbums()));
    }

    @Async("chillPool")
    @GetMapping("/search")
    public CompletableFuture<ResponseEntity<List<Album>>> search(String albumName, @RequestParam(required = false,
            defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(albumService.findAllByAlbumName(albumName)));
    }
    @PostMapping("/add")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Album>> saveOrUpdate(@RequestBody Album album) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(albumService.saveOrUpdate(album)));
    }

    @DeleteMapping("/delete")
    @Async("chillPool")
    public CompletableFuture<ResponseEntity<Boolean>> delete(long id) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(albumService.deleteAlbumById(id)));
    }
}
