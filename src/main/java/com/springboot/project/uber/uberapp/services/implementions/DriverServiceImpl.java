package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.dto.DriverDto;
import com.springboot.project.uber.uberapp.dto.RideDto;
import com.springboot.project.uber.uberapp.dto.RiderDto;
import com.springboot.project.uber.uberapp.entities.Driver;
import com.springboot.project.uber.uberapp.entities.Ride;
import com.springboot.project.uber.uberapp.entities.RideRequest;
import com.springboot.project.uber.uberapp.entities.enums.RideRequestStatus;
import com.springboot.project.uber.uberapp.entities.enums.RideStatus;
import com.springboot.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.springboot.project.uber.uberapp.repositories.DriverRepository;
import com.springboot.project.uber.uberapp.services.DriverService;
import com.springboot.project.uber.uberapp.services.PaymentService;
import com.springboot.project.uber.uberapp.services.RideRequestService;
import com.springboot.project.uber.uberapp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final PaymentService paymentService;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        logger.info("Insider acceptRide method for rideRequestId: {}", rideRequestId);
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("RideRequest cannot be accepted. Current status: " + rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();
        if(!currentDriver.isAvailable()){
            throw new RuntimeException("Driver is not available to accept rides.");
        }
        Driver savedDriver = updateDriverAvailability(currentDriver,false);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        logger.info("Ride created with id: {} for rideRequestId: {}", ride.getId(), rideRequestId);
        logger.info("Exit acceptRide method for rideRequestId: {}", rideRequestId);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        logger.info("Inside cancelRide method for rideId: {}", rideId);
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        // Validate if the driver is authorized to cancel the ride
        if(!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver is not authorized to cancel this ride.");
        }
        // Validate if the ride status allows cancellation
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled because ride status is not confirmed. Current status: " + ride.getRideStatus());
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability(driver,true);

        logger.info("Ride with id: {} has been cancelled.", rideId);
        logger.info("Exit cancelRide method for rideId: {}", rideId);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
        logger.info("Inside startRide method for rideId: {}", rideId);
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not authorized to start this ride.");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be started because ride status is not confirmed. Current status: " + ride.getRideStatus());
        }
        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("Invalid OTP provided to start the ride, ride cannot be started. provided otp: " + otp);
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

        paymentService.createNewPayment(savedRide);

        logger.info("Ride with id: {} has been started.", rideId);
        logger.info("Exit startRide method for rideId: {}", rideId);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not authorized to end this ride.");
        }
        if(!ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Ride cannot be ended because ride status is not ongoing. Current status: " + ride.getRideStatus());
        }
        ride.setEndedAt(LocalDateTime.now());
        Ride savedRIde = rideService.updateRideStatus(ride,RideStatus.ENDED);
        updateDriverAvailability(driver,true);
        paymentService.processPayment(ride);

        return modelMapper.map(savedRIde, RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        logger.info("Inside getMyProfile method for driver");
        Driver currentDriver = getCurrentDriver();
        logger.info("Exit getMyProfile method for driver with id: {}", currentDriver.getId());
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        logger.info("Inside getAllMyRides method for driver");
        Driver currentDriver = getCurrentDriver();
        Page<RideDto> rideDtos = rideService.getAllRidesOfDriver(currentDriver,pageRequest)
                .map(ride -> modelMapper.map(ride, RideDto.class));
        logger.info("Exit getAllMyRides method for driver with id: {}", currentDriver);
        return rideDtos;
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: 2"));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean isAvailable) {
        driver.setAvailable(isAvailable);
        return driverRepository.save(driver);
    }
}