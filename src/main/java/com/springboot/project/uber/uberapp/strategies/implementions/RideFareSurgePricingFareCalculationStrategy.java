package com.springboot.project.uber.uberapp.strategies.implementions;

import com.springboot.project.uber.uberapp.entities.RideRequest;
import com.springboot.project.uber.uberapp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service("rideFareSurgePricingFareCalculationStrategy")
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}