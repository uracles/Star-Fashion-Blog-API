package com.merakool.star_fashion.dto.request;

import com.merakool.star_fashion.enums.Gender;
import com.merakool.star_fashion.enums.Role;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class BlogUserRequestDto {
    @NotBlank(message = "please provide a name")
    private String username;

    @Email(message = "please provide a valid email")
    @NotBlank(message = "Email is required, please provide a valid email")
    private String email;

    @Size(min=6, max=10, message="password must be 6 to 10 characters")
    @NotBlank(message = "Provide your password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @Enumerated(EnumType.STRING)
    private Gender gender;
}
