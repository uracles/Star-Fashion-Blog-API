package com.merakool.star_fashion.services;

import com.merakool.star_fashion.dto.request.CommentRequestDto;
import com.merakool.star_fashion.dto.response.CommentResponseDto;
import com.merakool.star_fashion.entities.Comment;

import java.util.List;

public interface CommentService {
    CommentResponseDto makeComment(CommentRequestDto createComment, Long postId);

    Comment getCommentById(Long commentId);

    void deleteComment(Long commentId);

    List<Comment> getPostComment (Long postId);
}
