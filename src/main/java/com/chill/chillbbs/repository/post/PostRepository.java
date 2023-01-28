package com.chill.chillbbs.repository.post;

import com.chill.chillbbs.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 帖子rep
 *
 * @author Jarviz
 */
@Repository

public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * 模糊检索标题或内容
     *
     * @param keyword 关键字
     * @return 匹配结果
     */
    @Query(value = "select post from Post post where (post.title like concat('%',:keyword,'%') or post.content like concat('%',:keyword,'%')) ")
    List<Post> findAllByTitleOrContentLike(String keyword);
}
