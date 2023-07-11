package com.merakool.star_fashion.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Likes extends BaseEntity{

    private int count;

    @ManyToOne
    private Posts posts;

    @ManyToOne
    private Comments comments;

    @ManyToOne
    private Visitors visitors;


}
