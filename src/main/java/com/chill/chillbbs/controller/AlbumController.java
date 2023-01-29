package com.chill.chillbbs.controller;

import com.chill.chillbbs.entity.album.Album;
import com.chill.chillbbs.service.album.AlbumService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/album")
public class AlbumController {
    @Resource
    AlbumService albumService;

    @GetMapping("/allPage")
    public ResponseEntity<Object> getAllAlbumsPage(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size) {
        try {
            return ResponseEntity.ok(albumService.allAlbumsPage(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "albumName"))).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllAlbums() {
        try {
            return ResponseEntity.ok(albumService.allAlbums().get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(String albumName) {
        try {
            return ResponseEntity.ok(albumService.findAllByAlbumName(albumName).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/searchPage")
    public ResponseEntity<Object> searchPage(String albumName, @RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size) {
        try {
            return ResponseEntity.ok(albumService.findAllByAlbumNamePage(albumName, PageRequest.of(page, size)).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> saveOrUpdate(@RequestBody Album album) {
        try {
            return ResponseEntity.ok(albumService.saveOrUpdate(album).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(long id) {
        try {
            return ResponseEntity.ok(albumService.deleteAlbumById(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
