package com.springboot.project.uber.uberapp.services;

import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.entities.Driver;
import com.springboot.project.uber.uberapp.entities.Ride;
import com.springboot.project.uber.uberapp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//This is the RideService interface that defines the contract for ride-related operations in the Uber-like application.
public interface RideService {

    Ride getRideById(Long rideId);

    void  matchWithDriver(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}
