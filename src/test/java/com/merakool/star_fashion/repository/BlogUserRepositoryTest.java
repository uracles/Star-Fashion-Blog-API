package com.merakool.star_fashion.repository;

import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.repository.BlogUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BlogUserRepositoryTest {

    @Autowired
    private BlogUserRepository blogUserRepository;


    @Test
    public void testFindUserById() {
        Long userId = 1L;
        BlogUser user = new BlogUser();
        user.setId(userId);
        user.setUsername("esther");
        user.setEmail("esther@gmail.com");
        user.setPassword("esther33");


        Optional<BlogUser> blogUser = blogUserRepository.findById(userId);
        String expected = blogUser.get().getUsername();
        String actual = "esther";

        assertNotNull(blogUser.get());
        assertEquals(expected, actual);
    }

    @Test
    public void testExistsByEmail() {
        String email = "esther@gmail.com";
        when(blogUserRepository.existsByEmail(email)).thenReturn(true);

        assertTrue(blogUserRepository.existsByEmail(email));
    }

    @Test
    public void testFindByEmailAndPassword() {
        String email = "esther@gmail.com";
        String password = "esther33";

        BlogUser user = new BlogUser();
        user.setUsername("testingEsther");
        user.setEmail(email);
        user.setPassword(password);





}
