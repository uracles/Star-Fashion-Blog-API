package com.merakool.star_fashion.controller;

import com.merakool.star_fashion.dto.request.CommentRequestDto;
import com.merakool.star_fashion.entities.Comment;
import com.merakool.star_fashion.services.CommentService;
import com.merakool.star_fashion.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/make-comment")
    public ResponseEntity<?> createComment(@PathVariable("postId") Long postId,
                                           @Valid @RequestBody CommentRequestDto commentRequest){
        var response = commentService.makeComment(commentRequest, postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/viewPostComment/{id}")
    public List<Comment> viewPostComment(@PathVariable("id") Long postId){
        return commentService.getPostComment(postId);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
    }




}

