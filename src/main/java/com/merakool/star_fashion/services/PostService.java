package com.merakool.star_fashion.services;

import com.merakool.star_fashion.dto.request.PostRequestDto;
import com.merakool.star_fashion.dto.response.PostResponseDto;
import com.merakool.star_fashion.entities.Post;
import com.merakool.star_fashion.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;


public interface PostService {
    PostResponseDto uploadPost(PostRequestDto upLoadPostDto, Long blogUserId);

    Post getPostById(Long postId);

    Page<Post> getAllPost(Pageable pageable);

    String deletePost(Long postId);

    PostResponseDto likePost(Long userId, Long postId);

    PostResponseDto unlikePost(Long userId, Long postId);

    PostResponseDto modifyPost(PostRequestDto modifyPostDto, Long id);


}
