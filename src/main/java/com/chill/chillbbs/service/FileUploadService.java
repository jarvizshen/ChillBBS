package com.chill.chillbbs.service;

import com.chill.chillbbs.util.UploadType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
public interface FileUploadService {
    /**
     * 上传文件
     *
     * @param type    文件类别
     * @param files   文件
     * @param request 请求
     * @return 文件访问地址
     * @throws IOException io错误
     */
    @Async("chillPool")
    CompletableFuture<String> upload(UploadType type, MultipartFile[] files, HttpServletRequest request) throws IOException;
}
