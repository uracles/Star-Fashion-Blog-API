package com.merakool.star_fashion.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visitors extends BaseEntity{

    private String name;

    private String email;

    private String Password;

    @OneToMany (mappedBy = "visitors", cascade = CascadeType.ALL)
    private List<Comments> comments =  new ArrayList<>();

    @OneToMany (mappedBy = "visitors", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();
}
