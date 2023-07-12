package com.merakool.star_fashion.repository;

import com.merakool.star_fashion.entities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {
//    BlogUser findBlogUserByEmail(String email);

    boolean existsByEmail(String email);

    BlogUser findByEmailAndPassword(String email, String password);

}
