package com.merakool.star_fashion.repository;

import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.entities.Comment;
import com.merakool.star_fashion.entities.Like;
import com.merakool.star_fashion.entities.Post;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
// LIKES FOR POSTS
    Like findLikeByPostAndUser(Post post, BlogUser user);
    List<Like> findAllLikeByPost(Post post);

}



