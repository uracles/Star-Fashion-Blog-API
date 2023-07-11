package com.merakool.star_fashion.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comments extends BaseEntity{

    private String context;

    @ManyToOne
    private Posts posts;

    @OneToMany (mappedBy = "comments", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @ManyToOne
    private Visitors visitors;




}
