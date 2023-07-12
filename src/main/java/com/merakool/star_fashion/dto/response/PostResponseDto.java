package com.merakool.star_fashion.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponseDto {
    private String title;

    private String imageUrl;

    private String content;

    private Integer likes;

//    private Integer comments;
}
