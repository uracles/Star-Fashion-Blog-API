package com.merakool.star_fashion.services.implementation;

import com.merakool.star_fashion.dto.request.PostRequestDto;
import com.merakool.star_fashion.dto.response.PostResponseDto;
import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.entities.Category;
import com.merakool.star_fashion.entities.Post;
import com.merakool.star_fashion.enums.Role;
import com.merakool.star_fashion.exceptions.NotFoundException;
import com.merakool.star_fashion.exceptions.UnauthorizedException;
import com.merakool.star_fashion.repository.BlogUserRepository;
import com.merakool.star_fashion.repository.CategoryRepository;
import com.merakool.star_fashion.repository.PostRepository;
import com.merakool.star_fashion.services.PostService;
import com.merakool.star_fashion.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final BlogUserRepository blogUserRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PostResponseDto uploadPost(PostRequestDto upLoadPostDto, Long categoryId) {
        //find user by id
        BlogUser user = blogUserRepository.findById(upLoadPostDto.getBlogUser_id())
                .orElseThrow(() -> new NotFoundException("user does not exist"));

        //find by category
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("not found"));

        //check if blog user is an admin as only admins can post
        if (user.getRole().equals(Role.ADMIN)) {

            return Mapper.convertPostToPostResponseDto(postRepository.save(Post.builder()
                            .title(upLoadPostDto.getTitle())
                            .content(upLoadPostDto.getContent())
                            .category(category)
//                            .category(upLoadPostDto.getCategory())
                            .imageUrl(upLoadPostDto.getImageUrl())
                            .blogUser(blogUserRepository.findById(upLoadPostDto.getBlogUser_id())
                                    .orElseThrow(() -> new NotFoundException("user does not exist")))
                            .build()
            ));
        }
        throw new UnauthorizedException("Sorry! you do not have access");
    }


    @Override
    public Optional<Post> getPostById(Long postId) {
//        public Post getPostById(Long postId) {
        return postRepository.findById(postId);
//                .orElseThrow(() -> new NotFoundException("post doesnt exist"));
    }


    @Override
    public Page<Post> getAllPost(Pageable pageable) {
        List<Post> posts = postRepository.findAll();

        if (posts.isEmpty()) {
            throw new NotFoundException("There are no posts");
        }
        return postRepository.findAll(pageable);

    }

    @Override
    public void deletePost(Long postId) {

        BlogUser user = blogUserRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("user does not exist"));

        if (!user.getRole().equals(Role.ADMIN)) {
            throw new UnauthorizedException("Sorry! you dont have access");
        }
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("post doesn't"));
        postRepository.delete(post);

//        public String deleteTask(Long postId) {
//            postRepository.deleteById(postId);
//            return "post deleted successfully";

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
        post.setCategory(modifyPostDto.getCategory());

//        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);
        return Mapper.convertPostToPostResponseDto(post);
    }



    @Override
    public List<PostResponseDto> viewPostByCategory(Long categoryId) {
        Category category = categoryRepository.findCategoryById(categoryId)
                .orElseThrow(() -> new NotFoundException("this post doesnt not exist"));
        List<Post> posts = category.getPost();

        List<PostResponseDto> postResponse = new ArrayList<>();
        for (Post p : posts) {
            PostResponseDto result = PostResponseDto.builder()
                    .title(p.getTitle())
                    .imageUrl(p.getImageUrl())
                    .content(p.getContent())
                    .comments(p.getComments().size())
                    .build();
            postResponse.add(result);
        }
        return postResponse;

    }
}
