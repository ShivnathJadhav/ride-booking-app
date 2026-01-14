package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.entities.Driver;
import com.springboot.project.uber.uberapp.entities.Ride;
import com.springboot.project.uber.uberapp.entities.RideRequest;
import com.springboot.project.uber.uberapp.entities.Rider;
import com.springboot.project.uber.uberapp.entities.enums.RideRequestStatus;
import com.springboot.project.uber.uberapp.entities.enums.RideStatus;
import com.springboot.project.uber.uberapp.repositories.RideRepository;
import com.springboot.project.uber.uberapp.repositories.RideRequestRepository;
import com.springboot.project.uber.uberapp.services.RideRequestService;
import com.springboot.project.uber.uberapp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;
    private final RideRequestRepository rideRequestRepository;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(() ->
                new RuntimeException("Ride not found with id: " + rideId));
    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);
        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }


    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver,pageRequest);
    }

    private String generateRandomOTP () {
        Random random = new Random();
        int otp = random.nextInt(10000); // Generates a random number between 0 and 9999
        return String.format("%04d", otp); // Formats the number as a 4-digit string, padding with zeros if necessary
    }
}
