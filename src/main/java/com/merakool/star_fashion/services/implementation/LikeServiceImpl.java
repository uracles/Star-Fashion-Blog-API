package com.merakool.star_fashion.services.implementation;

import com.merakool.star_fashion.entities.Like;
import com.merakool.star_fashion.entities.Post;
import com.merakool.star_fashion.exceptions.NotFoundException;
import com.merakool.star_fashion.repository.LikeRepository;
import com.merakool.star_fashion.repository.PostRepository;
import com.merakool.star_fashion.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Override
    public int noOfLikesOnPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("post not found"));
        return post.getLikes().size();
    }


    @Override
    public void addLikeToPost(Long blogUserId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new NotFoundException("post was not found"));

        Like like = Like.builder()
                .post(post)
                .user(null)
                .count(0).build();

        post.getLikes().add(like);
        postRepository.save(post);
    }



    @Override
    public void deleteLikeFromPost(Long postLikeId) {

    }

}
