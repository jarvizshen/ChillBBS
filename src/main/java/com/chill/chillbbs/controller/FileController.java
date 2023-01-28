package com.chill.chillbbs.controller;

import com.chill.chillbbs.service.FileUploadService;
import com.chill.chillbbs.util.UploadType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
@RestController
@EnableAsync
@RequestMapping("/api/file")
public class FileController {
    @Resource
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    @Async("chillPool")
    public CompletableFuture<String> upload(UploadType type, MultipartFile[] files, HttpServletRequest request) throws IOException {
        return CompletableFuture.completedFuture(fileUploadService.upload(type, files, request));
    }
}
