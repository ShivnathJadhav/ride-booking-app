package com.springboot.project.uber.uberapp.strategies.implementions;

import com.springboot.project.uber.uberapp.entities.RideRequest;
import com.springboot.project.uber.uberapp.services.DistanceService;
import com.springboot.project.uber.uberapp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("rideFareSurgePricingFareCalculationStrategy")
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
    //  TODO Implement surge pricing logic here
        double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(), rideRequest.getDropLocation());
        return distance * RIDE_FARE_MULTIPLIER;
    }
}