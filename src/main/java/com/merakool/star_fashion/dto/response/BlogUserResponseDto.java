package com.merakool.star_fashion.dto.response;

import com.merakool.star_fashion.enums.Gender;
import com.merakool.star_fashion.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlogUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private Gender gender;
    private LocalDateTime createdAt;
}
