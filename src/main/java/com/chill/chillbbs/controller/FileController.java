package com.chill.chillbbs.controller;

import com.chill.chillbbs.service.FileService;
import com.chill.chillbbs.service.util.UploadType;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jarviz
 */
@RestController
@RequestMapping("/api/file")
public class FileController {
    @Resource
    FileService fileService;

    @PostMapping("/upload")
    public Object upload(UploadType type, MultipartFile[] files, HttpServletRequest request) {
        try {
            return fileService.upload(type, files, request).get();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/uploadPostFile")
    public Object uploadPostFile(MultipartFile[] files, Long postId, HttpServletRequest request) {
        try {
            return fileService.uploadPostFile(files, postId, request);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
