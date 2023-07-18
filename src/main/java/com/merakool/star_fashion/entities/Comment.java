package com.merakool.star_fashion.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment_table")
public class Comment extends BaseEntity{

    @Column(length = 250)
    private String commentText;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private Post post;


    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER) //CHECK THIS LAZY AND EAGER ON DB
    @JoinColumn
    private BlogUser blogUserId;


}
