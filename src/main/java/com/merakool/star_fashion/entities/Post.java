package com.merakool.star_fashion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merakool.star_fashion.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false, unique = false, length = 50)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

////    @JsonIgnore
//    @OneToMany (mappedBy = "post", cascade = CascadeType.ALL)
//    private List<Like> likes = new ArrayList<>();


    @ElementCollection
    private Set<Long> likes;

//    @JsonIgnore
    @OneToMany (mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn (name = "blogUser_id", referencedColumnName = "id")
    private BlogUser blogUser;


}
