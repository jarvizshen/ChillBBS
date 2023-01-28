package com.chill.chillbbs.service.impl;

import com.chill.chillbbs.service.FileUploadService;
import com.chill.chillbbs.util.UploadType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Jarviz
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${postFilepath}")
    String postFilePath;
    @Value("${commentFilepath}")
    String commentFilePath;
    @Value("${albumCoverPath}")
    String albumCoverPath;
    @Value("${userIconPath}")
    String userIconPath;

    @Override
    public String upload(UploadType type, MultipartFile[] files, HttpServletRequest request) throws IOException {
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
                file1.mkdirs();
            }
            file.transferTo(new File(file1, fileName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + menu + fileName;
            list.add(url);
        }
        return new ObjectMapper().writeValueAsString(list);
    }
}
