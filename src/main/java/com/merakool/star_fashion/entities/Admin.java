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
public class Admin extends BaseEntity{
    private String username;

    private String email;

    private String password;

    @OneToMany (mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Posts> posts = new ArrayList<>();



}
