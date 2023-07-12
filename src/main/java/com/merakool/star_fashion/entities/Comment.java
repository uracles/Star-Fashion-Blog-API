package com.merakool.star_fashion.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment_table")
public class Comment extends BaseEntity{

    @Column(length = 250)
    private String context;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private Post post;

//    @OneToMany (mappedBy = "comments", cascade = CascadeType.ALL)
//    private List<Likes> likes = new ArrayList<>();

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER) //CHECK THIS LAZY AND EAGER ON DB
    @JoinColumn
    private BlogUser userId;




}
