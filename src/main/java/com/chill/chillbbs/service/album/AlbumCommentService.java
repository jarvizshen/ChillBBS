package com.chill.chillbbs.service.album;

import com.chill.chillbbs.entity.album.AlbumComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 评论服务
 *
 * @author Jarviz
 */
public interface AlbumCommentService {
    /**
     * 获取一张专辑的所有评论
     *
     * @param id       专辑id
     * @return 所有相关评论
     */
    List<AlbumComment> getCommentsByAlbumId(Long id);

    /**
     * 删除所有对应专辑的评论
     *
     * @param albumId 专辑id
     */
    void deleteAllByAlbumId(Long albumId);

    /**
     * 添加评论
     *
     * @param albumComment 评论实体
     * @return 是否添加成功
     */
    Boolean add(AlbumComment albumComment);

    /**
     * 删除评论
     *
     * @param albumComment 评论实体
     * @return 是否删除成功
     */
    Boolean delete(AlbumComment albumComment);
}
