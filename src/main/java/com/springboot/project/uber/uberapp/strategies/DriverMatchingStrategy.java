package com.springboot.project.uber.uberapp.strategies;

import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);
}
