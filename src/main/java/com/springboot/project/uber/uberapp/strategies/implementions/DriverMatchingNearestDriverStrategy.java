package com.springboot.project.uber.uberapp.strategies.implementions;

import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.entities.Driver;
import com.springboot.project.uber.uberapp.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
