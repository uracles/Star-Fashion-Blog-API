package com.merakool.star_fashion.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_table")
public class Post extends BaseEntity{

    private String imageUrl;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false, unique = true,length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id_id", referencedColumnName = "category_id")
    private Category category;

//    @JsonIgnore
    @OneToMany (mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();
//    private Integer likes;

//    @JsonIgnore
    @OneToMany (mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn (name = "blogUser_id", referencedColumnName = "id")
    private BlogUser blogUser;


}
