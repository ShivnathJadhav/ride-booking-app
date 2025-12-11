package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.dto.DriverDto;
import com.springboot.project.uber.uberapp.dto.SignupDto;
import com.springboot.project.uber.uberapp.dto.UserDto;
import com.springboot.project.uber.uberapp.entities.User;
import com.springboot.project.uber.uberapp.entities.enums.Role;
import com.springboot.project.uber.uberapp.exceptions.RuntimeConflictsException;
import com.springboot.project.uber.uberapp.repositories.UserRepository;
import com.springboot.project.uber.uberapp.services.AuthService;
import com.springboot.project.uber.uberapp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        userRepository.findByEmail(signupDto.getEmail()).ifPresent(user -> {
            throw new RuntimeConflictsException("User with email " + signupDto.getEmail() + " already exists.");
        });

        User user = modelMapper.map(signupDto, User.class);
        user.setRoles(Set.of(Role.RIDER)); // By default, every new user will be a RIDER
        User savedUser = userRepository.save(user);

    //      Create user related entities if needed (e.g., Rider profile)
        riderService.createNewRiderProfile(savedUser);
    //      TODO add wallet creation logic here


        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onBoardNewDriver(Long userId) {
        return null;
    }
}
