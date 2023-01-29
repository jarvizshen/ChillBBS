package com.chill.chillbbs.repository.post;

import com.chill.chillbbs.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 帖子rep
 *
 * @author Jarviz
 */
@Repository

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    /**
     * 模糊检索标题或内容
     *
     * @param title   标题
     * @param content 内容
     * @return 匹配结果
     */
    List<Post> findAllByTitleOrContentLike(String title, String content);

    /**
     * 模糊检索标题或内容分页
     *
     * @param title    标题
     * @param content  内容
     * @param pageable 分页参数
     * @return 匹配结果分页
     */
    Page<Post> findAllByTitleOrContentLike(String title, String content, Pageable pageable);
}
