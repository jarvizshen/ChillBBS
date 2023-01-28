package com.chill.chillbbs.service;

import com.chill.chillbbs.util.UploadType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Jarviz
 */
public interface FileUploadService {
    /**
     * 上传文件
     *
     * @param type 文件类别
     * @param files 文件
     * @param request 请求
     * @return 文件访问地址
     * @throws IOException
     */
    String upload(UploadType type, MultipartFile[] files, HttpServletRequest request) throws IOException;
}
