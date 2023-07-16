package com.merakool.star_fashion.dto.response;

import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.entities.Post;
import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Data
@Builder
public class CommentResponseDto {

    private Long id;
    private String commentText;
    private Post post;
    private BlogUser userId;
}
