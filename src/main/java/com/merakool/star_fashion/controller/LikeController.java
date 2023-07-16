package com.merakool.star_fashion.controller;

import com.merakool.star_fashion.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/like")
public class LikeController {
    private final LikeService likeService;
    @PostMapping("like/{postId}/{userId}")
    public void likePost(@PathVariable Long postId, @PathVariable Long userId) {
        likeService.addLikeToPost(postId,userId);
    }

    @DeleteMapping("unlike/{postLikeId}")
    public void unlikePost(@PathVariable Long postLikeId) {
        likeService.deleteLikeFromPost(postLikeId);
    }

    @GetMapping("/noOfLikes/{id}")
    public int noOfLikesOnPost(@PathVariable("id") Long postId){
        return likeService.noOfLikesOnPost(postId);
    }

}
