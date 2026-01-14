package com.springboot.project.uber.uberapp.services;

import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.entities.Driver;
import com.springboot.project.uber.uberapp.entities.Ride;
import com.springboot.project.uber.uberapp.entities.RideRequest;
import com.springboot.project.uber.uberapp.entities.Rider;
import com.springboot.project.uber.uberapp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//This is the RideService interface that defines the contract for ride-related operations in the Uber-like application.
public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
