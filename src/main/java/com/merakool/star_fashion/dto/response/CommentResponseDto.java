package com.merakool.star_fashion.dto.response;

import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.entities.Post;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Data
@Builder
@Getter
@Setter
public class CommentResponseDto {

    private Long id;
    private String commentText;
    private String post;
    private String blogUser;
}
