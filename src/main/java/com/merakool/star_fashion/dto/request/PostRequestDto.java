package com.merakool.star_fashion.dto.request;

import com.merakool.star_fashion.entities.Category;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class PostRequestDto {
    private String imageUrl;

    private String content;

    private String title;

    private Category category;

    private Long blogUser_id;
}
