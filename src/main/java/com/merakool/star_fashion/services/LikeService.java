package com.merakool.star_fashion.services;

import com.merakool.star_fashion.entities.Post;

import java.util.List;

public interface LikeService {
    int noOfLikesOnPost(Long postId);
//    List<Post> getAllLikeFromPost(Post post);


    void addLikeToPost(Long blogUserId, Long postId);

    void deleteLikeFromPost(Long postLikeId);
}
