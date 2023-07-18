package com.merakool.star_fashion.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merakool.star_fashion.controller.BlogUserController;
import com.merakool.star_fashion.dto.request.BlogUserRequestDto;
import com.merakool.star_fashion.dto.response.BlogUserResponseDto;
import com.merakool.star_fashion.enums.Gender;
import com.merakool.star_fashion.enums.Role;
import com.merakool.star_fashion.services.BlogUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = BlogUserController.class) // Help us test Rest Endpoint PersonController
@AutoConfigureMockMvc(addFilters = false) // Remove and front filter eg: Security
@ExtendWith(MockitoExtension.class) //To Mock external components eg: PersonService
class BlogUserControllerTest {

    @MockBean
    private BlogUserService blogUserService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    BlogUserRequestDto userSignUpDto;
    BlogUserResponseDto userResponse;

    @BeforeEach
    void setUp() {
        System.out.println("SUCESSSSSSSSSSS!!!!!!");


        userSignUpDto = userSignUpDto.builder()
                .email("miracle@hotmail.com")
                .gender(Gender.MALE)
                .password("26734BT")
                .role(Role.CUSTOMERS)
                .username("merak0ol")
                .password("miracle202")
                .build();

        userResponse = BlogUserResponseDto.builder()
                .id(20L)
                .gender(Gender.MALE)
                .role(Role.CUSTOMERS)
                .createdAt(LocalDateTime.now())
                .email("miracle202")
                .username("merak0ol")
                .build();
    }

    @Test
    void testRegisterUser() throws Exception {
        when(blogUserService.createUser(userSignUpDto)).thenReturn(userResponse);

        ResultActions result = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSignUpDto)));

        result.andExpect(status().isOk());
//                .
    }


    @Test
    @DisplayName("Testing Dto")
    void testRegisterUser_IncorrectRequestDto() throws Exception {

        BlogUserRequestDto incorrectDto = BlogUserRequestDto.builder()
//                .email("miracle@hotmail.com")
//                .gender(Gender.MALE)
                .password("26734BT")
                .role(Role.CUSTOMERS)
                .username("merak0ol")
                .password("miracle202")
                .build();

        ResultActions result = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incorrectDto)));

        result.andExpect(status().isBadRequest()); //400
    }


    @Test
    void testLogIn() throws Exception {
        when(blogUserService.getUserByEmailAndPassword("miracle@hotmail.com", "miracle202"))
                .thenReturn(userResponse);

        ResultActions result = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSignUpDto)));

        result.andExpect(status().isOk());

//        result.andExpect(status().isBadRequest()) //400
//        result.andExpect(status().isInternalServerError()); //500
    }


    @Test
    void testFindAppUser() throws Exception {
        when(blogUserService.getUserById(20L)).thenReturn(userResponse);

        MvcResult result = mockMvc.perform(get("/api/{id}", 20L)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        //Assert
        int actual = result.getResponse().getStatus();
        Assertions.assertEquals(200, actual);
    }

    @Test
    void testLogOut() throws Exception {
        String expectedMessage = "User with ID 1 has been successfully logged out.";
        when(blogUserService.logOutUser(1L)).thenReturn(expectedMessage);

        ResultActions result = mockMvc.perform(get("/api/logout/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().string(expectedMessage));
    }
}
