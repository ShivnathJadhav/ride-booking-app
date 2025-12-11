package com.springboot.project.uber.uberapp.controllers;

import com.springboot.project.uber.uberapp.dto.SignupDto;
import com.springboot.project.uber.uberapp.dto.UserDto;
import com.springboot.project.uber.uberapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        UserDto userDto = authService.signup(signupDto);
        return ResponseEntity.ok(userDto);
    }
}
