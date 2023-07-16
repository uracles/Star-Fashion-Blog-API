package com.merakool.star_fashion.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CommentRequestDto {
    @NotBlank(message="comment cannot be null")
    private String context;

    @NotBlank(message="ID of commenter required")
    private Long userId;

    private Long postId;
}
