package com.chill.chillbbs.service.impl;

import com.chill.chillbbs.entity.file.PostFile;
import com.chill.chillbbs.repository.file.PostFileRepository;
import com.chill.chillbbs.service.FileService;
import com.chill.chillbbs.service.util.UploadType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Jarviz
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("${postFilepath}")
    String postFilePath;
    @Value("${commentFilepath}")
    String commentFilePath;
    @Value("${albumCoverPath}")
    String albumCoverPath;
    @Value("${userIconPath}")
    String userIconPath;
    @Resource
    PostFileRepository postFileRepository;

    @Override
    public CompletableFuture<String> upload(UploadType type, MultipartFile[] files, HttpServletRequest request) throws IOException {
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String suffix = originalFilename.substring(originalFilename.indexOf("."));
            String fileName = UUID.randomUUID() + suffix;
            File file1;
            String menu;
            switch (type) {
                case POST_FILE -> {
                    file1 = new File(postFilePath);
                    menu = "postFiles/";
                }
                case USER_ICON -> {
                    file1 = new File(userIconPath);
                    menu = "userIcons/";
                }
                case COMMENT_FILE -> {
                    file1 = new File(commentFilePath);
                    menu = "commentFiles/";
                }
                case ALBUM_COVER -> {
                    file1 = new File(albumCoverPath);
                    menu = "albumCovers/";
                }
                default -> throw new RuntimeException("文件类型不匹配");
            }
            if (!file1.exists()) {
                if (!file1.mkdirs()) {
                    throw new RuntimeException("创建路径失败");
                }
            }
            file.transferTo(new File(file1, fileName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + menu + fileName;
            list.add(url);
        }
        return CompletableFuture.completedFuture(new ObjectMapper().writeValueAsString(list));
    }

    @Override
    public CompletableFuture<String> uploadPostFile(MultipartFile[] files, Long postId, HttpServletRequest request) throws Exception {
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String suffix = originalFilename.substring(originalFilename.indexOf("."));
            String fileName = UUID.randomUUID() + suffix;
            File file1 = new File(postFilePath);
            String menu = "postFiles/";
            if (!file1.exists()) {
                if (!file1.mkdirs()) {
                    throw new RuntimeException("创建路径失败");
                }
            }
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + menu + fileName;
            list.add(url);
            file.transferTo(new File(file1, fileName));
            if (addPostFile(PostFile.builder().postId(postId).fileName(fileName)
                    .fileSize(file.getSize()).fileLink(url).createTime(new Date()).build()).get() == null) {
                throw new Exception("添加文件数据失败");
            }
        }
        return CompletableFuture.completedFuture(new ObjectMapper().writeValueAsString(list));
    }

    @Override
    public void deleteAllByPostId(Long id) {
        postFileRepository.findAllByPostId(id).forEach(postFile -> {
            Path path = Paths.get(postFilePath + postFile.getFileName());
            try {
                Files.deleteIfExists(path);
                deleteById(postFile.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<PostFile> addPostFile(PostFile postFile) {
        return CompletableFuture.completedFuture(postFileRepository.save(postFile));
    }

    @Override
    public void deleteById(Long id) {
        postFileRepository.deleteById(id);
    }
}
