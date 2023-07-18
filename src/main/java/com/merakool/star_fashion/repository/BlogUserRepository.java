package com.merakool.star_fashion.repository;

import com.merakool.star_fashion.entities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {
    Optional<BlogUser> findById(Long blogUserId);

    boolean existsByEmail(String email);

    BlogUser findByEmailAndPassword(String email, String password);

}
