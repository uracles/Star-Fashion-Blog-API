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
@Table(name = "category_table")
public class Category {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long id;

    private String name;

    @OneToMany (mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> post = new ArrayList<>();


}
