package com.merakool.star_fashion.controller;

import com.merakool.star_fashion.dto.request.BlogUserRequestDto;
import com.merakool.star_fashion.services.BlogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogUserController {
    private final BlogUserService blogUserService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid BlogUserRequestDto userSignUpDto) {
        var response = blogUserService.createUser(userSignUpDto);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<?> logIn(@Valid @RequestBody BlogUserRequestDto userLogInDto) {
        var loginResponse = blogUserService.getUserByEmailAndPassword(userLogInDto.getEmail(), userLogInDto.getPassword());
        return ResponseEntity.ok(loginResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findAppUser(@PathVariable Long id) {
        var response = blogUserService.getUserById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/logout/{userId}")
    public ResponseEntity<String> logOut(@PathVariable Long userId) {
        var response = blogUserService.logOutUser(userId);
        return ResponseEntity.ok(response);
    }
}
