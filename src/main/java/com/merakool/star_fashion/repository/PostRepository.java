package com.merakool.star_fashion.repository;

import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.entities.Post;
import com.merakool.star_fashion.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    Post findByBlogUserIdAndId(Long blogUserId, Long postId);
    Post findPostsByBlogUserIdAndId(Long blogUserId, Long postId);

}
