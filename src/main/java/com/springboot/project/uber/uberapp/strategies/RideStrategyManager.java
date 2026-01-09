package com.springboot.project.uber.uberapp.strategies;

import com.springboot.project.uber.uberapp.strategies.implementions.DriverMatchingHighestRatedDriverStrategy;
import com.springboot.project.uber.uberapp.strategies.implementions.DriverMatchingNearestDriverStrategy;
import com.springboot.project.uber.uberapp.strategies.implementions.RideFareDefaultFareCalculationStrategy;
import com.springboot.project.uber.uberapp.strategies.implementions.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy rideFareSurgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy (double riderRatingThreshold){
        if(riderRatingThreshold >=4.5){
            return driverMatchingHighestRatedDriverStrategy;
        } else {
            return driverMatchingNearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime sugeStartTime = LocalTime.of(21,0); // 9 PM
        LocalTime sugeEndTime = LocalTime.of(07,0);   // 7 AM
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(sugeStartTime) && currentTime.isBefore(sugeEndTime);
        if(isSurgeTime){
            return rideFareSurgePricingFareCalculationStrategy;
        } else {
            return rideFareDefaultFareCalculationStrategy;
        }
    }
}
