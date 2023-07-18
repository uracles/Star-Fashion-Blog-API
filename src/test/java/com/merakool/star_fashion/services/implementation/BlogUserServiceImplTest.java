package com.merakool.star_fashion.services.implementation;

import com.merakool.star_fashion.dto.request.BlogUserRequestDto;
import com.merakool.star_fashion.dto.response.BlogUserResponseDto;
import com.merakool.star_fashion.entities.BlogUser;
import com.merakool.star_fashion.enums.Gender;
import com.merakool.star_fashion.enums.Role;
import com.merakool.star_fashion.exceptions.NotFoundException;
import com.merakool.star_fashion.repository.BlogUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class BlogUserServiceImplTest {

    @Mock
    private BlogUserRepository blogUserRepository;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private BlogUserServiceImpl blogUserService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void testCreateUser() {
        BlogUserRequestDto userSignUpDto = new BlogUserRequestDto("daniel",
                "daniel@gmail.com", "daniel777", Role.ADMIN, Gender.MALE);

        BlogUser blogUser = BlogUser.builder()
                .id(1L)
                .username(userSignUpDto.getUsername())
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .role(userSignUpDto.getRole())
                .gender(userSignUpDto.getGender())
                .build();

        when(blogUserRepository.existsByEmail(userSignUpDto.getEmail())).thenReturn(false);

        when(blogUserRepository.save(any(BlogUser.class))).thenReturn(blogUser);

        BlogUserResponseDto actual = blogUserService.createUser(userSignUpDto);

        assertNotNull(actual);


        assertEquals(blogUser.getId(), actual.getId());
        assertEquals(blogUser.getUsername(), actual.getUsername());
        assertEquals(blogUser.getEmail(), actual.getEmail());
        assertEquals(blogUser.getRole(), actual.getRole());
        assertEquals(blogUser.getGender(), actual.getGender());

        verify(blogUserRepository, times(1)).existsByEmail(userSignUpDto.getEmail());
        verify(blogUserRepository, times(1)).save(any(BlogUser.class));
    }



    @Test
    void testCreateUser_UserAlreadyExists() {
        BlogUserRequestDto userSignUpDto = new BlogUserRequestDto("daniel",
                "daniel@gmail.com", "daniel777", Role.ADMIN, Gender.MALE);

        userSignUpDto.setEmail("daniel@gmail.com");

        when(blogUserRepository.existsByEmail(userSignUpDto.getEmail())).thenReturn(true);

        assertThrows(NotFoundException.class, () -> blogUserService.createUser(userSignUpDto));

//        verify(blogUserRepository, times(1)).existsByEmail(userSignUpDto.getEmail());
//        verify(blogUserRepository, never()).save(any(BlogUser.class));
    }


    @Test
    void testGetUserByEmailAndPassword() {
        String email = "daniel@gmail.com";
        String password = "daniel777";
        BlogUser user = BlogUser.builder()
                .id(1L)
                .username("JohnDoe")
                .email(email)
                .password(password)
                .role(Role.ADMIN)
                .gender(Gender.MALE)
                .build();

        when(blogUserRepository.findByEmailAndPassword(email, password)).thenReturn(user);

        BlogUserResponseDto responseDto = blogUserService.getUserByEmailAndPassword(email, password);

        assertNotNull(responseDto);
//        assertEquals(user.getId(), responseDto.getId());
//        assertEquals(user.getUsername(), responseDto.getUsername());
//        assertEquals(user.getEmail(), responseDto.getEmail());
//        assertEquals(user.getRole(), responseDto.getRole());
//        assertEquals(user.getGender(), responseDto.getGender());
//
//        verify(blogUserRepository, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    void testGetUserByEmailAndPassword_UserNotFound() {
        String email = "daniel@gmail.com";
        String password = "1234567";

        when(blogUserRepository.findByEmailAndPassword(email, password)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> blogUserService.getUserByEmailAndPassword(email, password));

        verify(blogUserRepository, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    void testFindUserById() {
        Long blogUserId = 1L;
        BlogUser user = BlogUser.builder()
                .id(blogUserId)
                .username("daniel")
                .email("daniel@gmail.com")
                .password("password")
                .role(Role.ADMIN)
                .gender(Gender.MALE)
                .build();

        when(blogUserRepository.findById(blogUserId)).thenReturn(Optional.of(user));

        BlogUserResponseDto actual = blogUserService.getUserById(blogUserId);

        assertNotNull(actual);
        assertEquals(user.getId(), actual.getId());
        assertEquals(user.getUsername(), actual.getUsername());
        assertEquals(user.getEmail(), actual.getEmail());
        assertEquals(user.getRole(), actual.getRole());
        assertEquals(user.getGender(), actual.getGender());

        verify(blogUserRepository, times(1)).findById(blogUserId);
    }

    @Test
    void testFindUserById_UserNotFound() {
        Long userId = 1L;

        when(blogUserRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> blogUserService.getUserById(userId));
        verify(blogUserRepository, times(1)).findById(userId);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        BlogUser user = BlogUser.builder()
                .id(userId)
                .username("daniel")
                .email("daniel@gmail.com")
                .password("password")
                .role(Role.ADMIN)
                .gender(Gender.MALE)
                .build();

        when(blogUserRepository.findById(userId)).thenReturn(Optional.of(user));

        String result = blogUserService.deleteUser(userId);

        assertEquals("user deleted successfully", result);

        verify(blogUserRepository, times(1)).findById(userId);
        verify(blogUserRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        Long userId = 1L;

        when(blogUserRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> blogUserService.deleteUser(userId));

        verify(blogUserRepository, times(1)).findById(userId);
        verify(blogUserRepository, never()).delete(any(BlogUser.class));
    }

    @Test
    void testLogOutUser() {
        Long userId = 1L;
        String expectedMessage = "User with ID " + userId + " has been successfully logged out.";
        String actual = blogUserService.logOutUser(userId);

        assertEquals(expectedMessage, actual);

        // Verify that the invalidate method is called on the HttpSession
        verify(httpSession, times(1)).invalidate();
    }
}
