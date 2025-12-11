package com.springboot.project.uber.uberapp.strategies;

import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.entities.RideRequest;

//This strategy interface defines the contract for calculating ride fares based on different strategies.
public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}
