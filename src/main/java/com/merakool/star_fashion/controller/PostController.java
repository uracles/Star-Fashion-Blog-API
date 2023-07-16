package com.merakool.star_fashion.controller;

import com.merakool.star_fashion.dto.request.PostRequestDto;
import com.merakool.star_fashion.entities.Post;
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

    @PostMapping("/make-post/{id}")
//    @PostMapping("/{id}")
    public ResponseEntity<?> createPost(@PathVariable("id") Long categoryId,
                                        @Valid @RequestBody PostRequestDto upLoadPostDto) {
        var response = postService.uploadPost(upLoadPostDto, categoryId);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/modifyPost/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long postId,
                                        @Valid @RequestBody PostRequestDto updatePostDto) {
        var response = postService.modifyPost(updatePostDto, postId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/viewAllPost")
//    public Page<Post> viewAllPost(Pageable pageable) {
//        return postService.getAllPost(pageable);

    public ResponseEntity<?> viewAllPost(Pageable pageable) {
        var response = postService.getAllPost(pageable);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/postByCategoryId/{categoryId}")
    public ResponseEntity<List<?>> viewPostByCategoryId(@PathVariable("categoryId") Long categoryId) {
        var response = postService.viewPostByCategory(categoryId);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);

//    @DeleteMapping("/delete/{postId}")
//    public void deletePost(@PathVariable Long postId) {
//        postService.deletePost(postId);
//    }
    }
}
