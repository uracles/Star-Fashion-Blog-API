package com.merakool.star_fashion.services.implementation;

import com.merakool.star_fashion.dto.request.BlogUserRequestDto;
import com.merakool.star_fashion.dto.response.BlogUserResponseDto;
import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.exceptions.NotFoundException;
import com.merakool.star_fashion.repository.BlogUserRepository;
import com.merakool.star_fashion.services.BlogUserService;
import com.merakool.star_fashion.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogUserServiceImpl implements BlogUserService {

    private final BlogUserRepository blogUserRepository;
    private final HttpSession httpSession;
    @Override
    public BlogUserResponseDto createUser(BlogUserRequestDto userSignUpDto) {
        String userEmail = userSignUpDto.getEmail();
        boolean appUserExist = blogUserRepository.existsByEmail(userEmail);
        if (appUserExist)
            throw new NotFoundException("User already exist, provide a different email");

        return Mapper.blogUserToBlogUserResponseDto(blogUserRepository.save(BlogUser
                .builder()

                .username(userSignUpDto.getUsername())
                .password(userSignUpDto.getPassword())
                .email(userSignUpDto.getEmail())
                .role(userSignUpDto.getRole())
//                .createdAt(LocalDateTime.now())
                .gender(userSignUpDto.getGender())
                .build()
        ));
    }

    @Override
    public BlogUserResponseDto getUserByEmailAndPassword(String email, String password) throws NotFoundException {

        BlogUser blogUser = blogUserRepository.findByEmailAndPassword(email, password);
//                .orElseThrow(()-> new NotFoundException("User not Found OR wrong email and password"));
        return Mapper.blogUserToBlogUserResponseDto(blogUser);

    }

    @Override
    public BlogUserResponseDto getUserById(Long id) {
        return Mapper.blogUserToBlogUserResponseDto(blogUserRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("User Not Found")
        ));
    }

    @Override
    public String deleteUser(Long blogUserId) {
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findById(blogUserId);
        if (optionalBlogUser.isEmpty()) {
            throw new NotFoundException("user not found");
        } else {
            BlogUser blogUser = optionalBlogUser.get();
            blogUserRepository.delete(blogUser);

            return "user deleted successfully";
        }
    }

    @Override
    public String logOutUser(Long userId) {
        httpSession.invalidate();
        return "User with ID " + userId + " has been successfully logged out.";
    }
}







