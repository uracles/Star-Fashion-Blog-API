package com.merakool.star_fashion.dto.response;

import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.entities.Comment;
import com.merakool.star_fashion.enums.Category;
import com.merakool.star_fashion.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostResponseDto {
    private Long id;

    private String title;

    private String imageUrl;

    private String content;

    private Category category;

    private String blogUser;

    private String likes;

    private List<String> comments;
}
