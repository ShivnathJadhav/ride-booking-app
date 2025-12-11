package com.springboot.project.uber.uberapp.services;

import com.springboot.project.uber.uberapp.dto.DriverDto;
import com.springboot.project.uber.uberapp.dto.SignupDto;
import com.springboot.project.uber.uberapp.dto.UserDto;

// This service handles authentication-related operations such as login, signup, and onboarding new drivers.
public interface AuthService {
    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onBoardNewDriver(Long userId);
}