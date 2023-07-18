package com.merakool.star_fashion.dto.request;

import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.enums.Category;
import com.merakool.star_fashion.enums.Role;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class PostRequestDto {
    private String imageUrl;

    @NotBlank(message = "post content is needed")
    private String content;

    @NotBlank(message = "Title is needed")
    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Role role;

    private BlogUser blogUser;
}
