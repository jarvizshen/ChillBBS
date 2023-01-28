package com.chill.chillbbs.repository.post;

import com.chill.chillbbs.entity.post.PostCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jarviz
 */
@Repository

public interface PostCollectionRepository extends JpaRepository<PostCollection, Long> {
}
