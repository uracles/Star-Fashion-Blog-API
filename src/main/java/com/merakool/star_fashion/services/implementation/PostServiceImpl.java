package com.merakool.star_fashion.services.implementation;

import com.merakool.star_fashion.dto.request.PostRequestDto;
import com.merakool.star_fashion.dto.response.PostResponseDto;
import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.entities.Comment;
import com.merakool.star_fashion.entities.Post;
import com.merakool.star_fashion.enums.Category;
import com.merakool.star_fashion.enums.Role;
import com.merakool.star_fashion.exceptions.NotFoundException;
import com.merakool.star_fashion.exceptions.UnauthorizedException;
import com.merakool.star_fashion.repository.BlogUserRepository;
import com.merakool.star_fashion.repository.CommentRepository;
import com.merakool.star_fashion.repository.PostRepository;
import com.merakool.star_fashion.services.PostService;
import com.merakool.star_fashion.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final BlogUserRepository blogUserRepository;
    private final CommentRepository commentRepository;

    @Override
    public PostResponseDto uploadPost(PostRequestDto upLoadPostDto, Long blogUserId) {
        //find user by id
        BlogUser user = blogUserRepository.findById(blogUserId)
                .orElseThrow(() -> new NotFoundException("user does not exist"));

        //check if blog user is an admin as only admins can post
        if (user.getRole() == Role.ADMIN) {
            // Create a new Post entity
            Post post = new Post();
            post.setTitle(upLoadPostDto.getTitle());
            post.setContent(upLoadPostDto.getContent());
            post.setCategory(upLoadPostDto.getCategory());
            post.setImageUrl(upLoadPostDto.getImageUrl());

            // Set the blog user for the post
            post.setBlogUser(user);

            // Save the post in the database
            Post savedPost = postRepository.save(post);

            // Convert and return the response DTO
            return Mapper.convertPostToPostResponseDto(savedPost);

        }

        throw new UnauthorizedException("Sorry! you do not have access");
    }


    //
    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found with ID: " + postId));
//                .orElseThrow(() -> new NotFoundException("post doesnt exist"));
    }


    @Override
//    public Page<Post> getAllPost(Pageable pageable) {
//        List<Post> posts = postRepository.findAll();
//
//        if (posts.isEmpty()) {
//            throw new NotFoundException("There are no posts");
//        }
//
//      return postRepository.findAll(pageable);
//    }

    public Page<Post> getAllPost(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);

        if (posts.isEmpty()) {
            throw new NotFoundException("There are no posts");
        }

        posts.forEach(post -> post.getComments().size());

        return posts;
    }


    @Override
//    public void deletePost(Long postId) {

        public String deletePost(Long postId){
            postRepository.deleteById(postId);
            return "post deleted successfully";

        }


    @Override
    public PostResponseDto modifyPost(PostRequestDto modifyPostDto, Long postId) {
//        BlogUser user = blogUserRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("user does not exist"));

        Optional<Post> postOptional = postRepository.findById(postId);

        if (!postOptional.isPresent()) {
            throw new NotFoundException("Post not found");
        }

        Post post = postOptional.get();

        post.setTitle(modifyPostDto.getTitle());
        post.setContent(modifyPostDto.getContent());
        post.setImageUrl(modifyPostDto.getImageUrl());
//        post.setCategory(modifyPostDto.getCategory());

//        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);
        return Mapper.convertPostToPostResponseDto(post);
    }







    public PostResponseDto likePost(Long userId, Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {

            Post post = postOptional.get();
            if (!post.getLikes().contains(userId)) {
                post.getLikes().add(userId);
                postRepository.save(post);

            }

            return Mapper.convertLikeToPostResponseDto(postRepository.save(post));
        } else {
            throw new NotFoundException("Post not found with ID: " + postId);
        }
    }


    public PostResponseDto unlikePost(Long userId, Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (post.getLikes().contains(userId)) {
                post.getLikes().remove(userId);
                postRepository.save(post);
            }

            return Mapper.convertLikeToPostResponseDto(postRepository.save(post));

        } else {
            throw new NotFoundException("Post not found with ID: " + postId);
        }
    }











//    @Override
//    public List<PostResponseDto> viewPostByCategory(Category category, Long postId) {
//        return postRepository
//                .findPostByPostIdAndCategory(postId, category)
//                .stream()
//                .map(Mapper::convertPostToPostResponseDto)
//                .collect(Collectors.toList());
//    }

}
