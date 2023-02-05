package com.chill.chillbbs.repository.file;

import com.chill.chillbbs.entity.file.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jarviz
 */
public interface PostFileRepository extends JpaRepository<PostFile, Long> {
    /**
     * 删除话题对应的所有附件
     *
     * @param postId 话题id
     */
    void deleteAllByPostId(Long postId);

    /**
     * 获取话题对应的所有附件
     *
     * @param postId 话题id
     * @return 附件列表
     */
    List<PostFile> findAllByPostId(Long postId);
}
