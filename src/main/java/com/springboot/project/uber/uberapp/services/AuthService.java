package com.springboot.project.uber.uberapp.services;

import com.springboot.project.uber.uberapp.dto.DriverDto;
import com.springboot.project.uber.uberapp.dto.SignupDto;
import com.springboot.project.uber.uberapp.dto.UserDto;

public interface AuthService {
    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onBoardNewDriver(Long userId);
}
