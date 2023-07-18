package com.merakool.star_fashion.dto.request;

import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.entities.Post;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CommentRequestDto {
    @NotBlank(message="comment cannot be null")
    private String commentText;

    private BlogUser blogUser;

    private Post post;


}
