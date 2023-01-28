package com.chill.chillbbs.repository.album;

import com.chill.chillbbs.entity.album.AlbumComment;
import com.chill.chillbbs.entity.post.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论rep
 *
 * @author Jarviz
 */
@Repository

public interface AlbumCommentRepository extends JpaRepository<AlbumComment, Long> {
    /**
     * 删除所有对应专辑的评论
     *
     * @param albumId 专辑id
     */
    void deleteAllByAlbumId(Long albumId);

    /**
     * 获取所有对应专辑的评论
     *
     * @param albumId 话题id
     * @return 所有对应评论
     */
    List<AlbumComment> findAllByAlbumId(Long albumId);
}
