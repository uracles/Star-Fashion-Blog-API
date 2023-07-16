package com.merakool.star_fashion.dto.response;

import com.merakool.star_fashion.entities.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponseDto {
    private Long id;

    private String title;

    private String imageUrl;

    private String content;

    private Category category;

    private Integer likes;

    private Integer comments;
}
