package com.springboot.project.uber.uberapp.strategies;

import com.springboot.project.uber.uberapp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);
}
