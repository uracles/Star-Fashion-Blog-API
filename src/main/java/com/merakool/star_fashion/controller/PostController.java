package com.merakool.star_fashion.controller;

import com.merakool.star_fashion.dto.request.PostRequestDto;
import com.merakool.star_fashion.entities.Post;
import com.merakool.star_fashion.enums.Category;
import com.merakool.star_fashion.services.LikeService;
import com.merakool.star_fashion.services.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/make-post/{blogUserId}")
    public ResponseEntity<?> createPost(@PathVariable("blogUserId")Long blogUserId,
                                        @Valid @RequestBody PostRequestDto upLoadPostDto) {
        var response = postService.uploadPost(upLoadPostDto, blogUserId);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/modifyPost/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable("postId") Long postId,
                                        @Valid @RequestBody PostRequestDto updatePostDto) {
        var response = postService.modifyPost(updatePostDto, postId);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/viewAllPost")
    public ResponseEntity<Page<Post>> viewAllPost(Pageable pageable) {
        Page<Post> response = postService.getAllPost(pageable);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<?> deletePost(@PathVariable("postId") Long postId) {
        var response = postService.deletePost(postId);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/{userId}/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        var totalLikes = postService.likePost(userId, postId);
        return ResponseEntity.ok(totalLikes);
    }

    @PostMapping("/{userId}/{postId}/unlike")
    public ResponseEntity<?> unlikePost(@PathVariable Long postId, @PathVariable Long userId) {
        var totalLikes = postService.unlikePost(userId, postId);
        return ResponseEntity.ok(totalLikes);
    }

}
