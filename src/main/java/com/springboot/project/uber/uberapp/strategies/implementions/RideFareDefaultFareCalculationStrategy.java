package com.springboot.project.uber.uberapp.strategies.implementions;

import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
