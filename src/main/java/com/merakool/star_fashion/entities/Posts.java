package com.merakool.star_fashion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Posts extends BaseEntity{

    private String pictureUrl;

    @Column(nullable = false,unique = true,length = 50)
    private String title;

    @JsonIgnore
    @OneToMany (mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @JsonIgnore
    @OneToMany (mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();


}
