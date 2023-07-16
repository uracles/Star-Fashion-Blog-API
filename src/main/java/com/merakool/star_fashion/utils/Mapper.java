package com.merakool.star_fashion.utils;

import com.merakool.star_fashion.dto.response.BlogUserResponseDto;
import com.merakool.star_fashion.dto.response.CommentResponseDto;
import com.merakool.star_fashion.dto.response.PostResponseDto;
import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.entities.Comment;
import com.merakool.star_fashion.entities.Post;
import com.merakool.star_fashion.repository.CommentRepository;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Builder
@Getter
public class Mapper {
    public static BlogUserResponseDto blogUserToBlogUserResponseDto(BlogUser user) {
        return BlogUserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
//                .role(user.getRole())
//                .gender(user.getGender())
//                .createdAt(LocalDateTime.now())

                .build();
    }

    public static PostResponseDto convertPostToPostResponseDto(Post post) {
        return PostResponseDto.builder()
//                .id(post.getId())

                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .imageUrl(post.getImageUrl())

//                .comments(new ArrayList<>())

//                .createdAt(post.getCreatedAt())
                .build();
    }

    public static CommentResponseDto generateCommentToCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()

                .id(comment.getId())
                .context(comment.getContext())
                .build();
    }
}
