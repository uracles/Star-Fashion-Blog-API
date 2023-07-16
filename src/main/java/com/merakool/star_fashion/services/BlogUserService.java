package com.merakool.star_fashion.services;

import com.merakool.star_fashion.dto.request.BlogUserRequestDto;
import com.merakool.star_fashion.dto.response.BlogUserResponseDto;
import com.merakool.star_fashion.entities.BlogUser;

public interface BlogUserService {
    BlogUserResponseDto createUser(BlogUserRequestDto userSignUpDto);

    BlogUserResponseDto getUserByEmailAndPassword(String email, String password);

    BlogUserResponseDto getUserById(Long id);

    String deleteUser(Long blogUserId);

    String logOutUser();

}
