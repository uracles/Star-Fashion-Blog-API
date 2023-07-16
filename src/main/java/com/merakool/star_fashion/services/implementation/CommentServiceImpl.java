package com.merakool.star_fashion.services.implementation;

import com.merakool.star_fashion.dto.request.CommentRequestDto;
import com.merakool.star_fashion.dto.response.CommentResponseDto;
import com.merakool.star_fashion.entities.Comment;
import com.merakool.star_fashion.entities.Post;
import com.merakool.star_fashion.exceptions.NotFoundException;
import com.merakool.star_fashion.repository.BlogUserRepository;
import com.merakool.star_fashion.repository.CommentRepository;
import com.merakool.star_fashion.repository.PostRepository;
import com.merakool.star_fashion.services.CommentService;
import com.merakool.star_fashion.services.PostService;
import com.merakool.star_fashion.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final BlogUserRepository blogUserRepository;
    private final PostService postService;

    @Override
    public CommentResponseDto makeComment(CommentRequestDto createComment) {
        return Mapper.generateCommentToCommentResponseDto(commentRepository.save(
                Comment.builder()
                        .context(createComment.getContext())
                        .post(postService.getPostById(createComment.getPostId())
                                .orElseThrow(() -> new NotFoundException("post doesn't exist")))
                        .userId(blogUserRepository.findById(createComment.getUserId())
                                .orElseThrow(() -> new NotFoundException("this user doesn't exist in the database")))
                        .build()
        ));
    }


    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                ()-> new NotFoundException("No comment with this ID in database"));
    }


    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }


    @Override
    public List<Comment> getPostComment(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new NotFoundException("post not found"));
        return post.getComments();
    }
}
