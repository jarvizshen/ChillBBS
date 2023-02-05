package com.chill.chillbbs.service;

import com.chill.chillbbs.entity.file.PostFile;
import com.chill.chillbbs.service.util.UploadType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Jarviz
 */
public interface FileService {
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

    /**
     * 上传文件
     *
     * @param files   文件
     * @param postId  话题id
     * @param request 请求
     * @return 文件访问地址
     * @throws Exception 错误
     */
    @Async("chillPool")
    CompletableFuture<String> uploadPostFile(MultipartFile[] files, Long postId, HttpServletRequest request) throws Exception;

    /**
     * 删除话题对应的所有附件
     *
     * @param id 话题id
     */
    @Async("chillPool")
    void deleteAllByPostId(Long id);

    /**
     * 添加话题附件
     *
     * @param postFile 话题附件
     * @return 附件
     */
    @Async("chillPool")
    @SneakyThrows
    CompletableFuture<PostFile> addPostFile(PostFile postFile);

    /**
     * 根据id删除对应话题附件
     *
     * @param id 话题附件id
     */
    @Async("chillPool")
    @SneakyThrows
    void deleteById(Long id);
}
