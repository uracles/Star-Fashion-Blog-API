package com.merakool.star_fashion.services;

import com.merakool.star_fashion.dto.request.PostRequestDto;
import com.merakool.star_fashion.dto.response.PostResponseDto;
import com.merakool.star_fashion.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;


public interface PostService {
    PostResponseDto uploadPost(PostRequestDto upLoadPostDto, Long category);

    Optional<Post> getPostById(Long postId);

//    Page<Post> getAllPost(PostPage postPage);

    Page<Post> getAllPost(Pageable pageable);

    void deletePost(Long postId);

    PostResponseDto modifyPost(PostRequestDto modifyPostDto, Long id);

    List<PostResponseDto> viewPostByCategory(Long categoryId);
}
