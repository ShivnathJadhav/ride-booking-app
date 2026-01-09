package com.springboot.project.uber.uberapp.strategies;

import com.springboot.project.uber.uberapp.entities.Driver;
import com.springboot.project.uber.uberapp.entities.RideRequest;

import java.util.List;
// This strategy interface defines the contract for matching drivers to ride requests.
public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
