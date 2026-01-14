package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.dto.DriverDto;
import com.springboot.project.uber.uberapp.dto.RideDto;
import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.dto.RiderDto;
import com.springboot.project.uber.uberapp.entities.*;
import com.springboot.project.uber.uberapp.entities.enums.RideRequestStatus;
import com.springboot.project.uber.uberapp.entities.enums.RideStatus;
import com.springboot.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.springboot.project.uber.uberapp.repositories.RideRequestRepository;
import com.springboot.project.uber.uberapp.repositories.RiderRepository;
import com.springboot.project.uber.uberapp.services.DriverService;
import com.springboot.project.uber.uberapp.services.RideService;
import com.springboot.project.uber.uberapp.services.RiderService;
import com.springboot.project.uber.uberapp.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final Logger logger = LoggerFactory.getLogger(RiderServiceImpl.class);

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        logger.info("Inside requestRide method for rider");
        Rider rider = getCurrentRider();

        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        logger.info("Rider requested a ride from {} to {} using payment method {}",
                rideRequest.getPickUpLocation(), rideRequest.getDropLocation(), rideRequest.getPaymentMethod());

        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        Double estimatedFare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(estimatedFare);
        rideRequest.setRider(rider);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);
//      TODO: send notifications to drivers about this ride request.

        logger.info("Found {} drivers matching the ride request criteria", drivers.size());
        logger.info("Exiting requestRide method for rider");
        return modelMapper.map(rideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        logger.info("Inside cancelRide method for rider for rideId: {}", rideId);
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);
        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider is not authorized to cancel this ride with id: " + rideId);
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled because ride status is not confirmed. Current status: " + ride.getRideStatus());
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(), true);
        logger.info("Ride with id: {} has been cancelled by rider", rideId);
        logger.info("Exiting cancelRide method for rider for rideId: {}", rideId);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        logger.info("Inside getMyProfile method for rider");
        Rider currentRider = getCurrentRider();
        RiderDto riderDto = modelMapper.map(currentRider, RiderDto.class);
        logger.info("Exiting getMyProfile method for rider with id: {}", currentRider.getId());
        return riderDto;
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        logger.info("Inside getAllMyRides method for rider");
        Rider rider = getCurrentRider();
        Page<RideDto> rideDtos = rideService.getAllRidesOfRider(rider,pageRequest)
                .map(ride -> modelMapper.map(ride, RideDto.class));
        logger.info("Exit getAllMyRides method for rider with id: {}", rider);
        return rideDtos;
    }

    @Override
    public Rider createNewRiderProfile(User user) {
        logger.info("Inside createNewRiderProfile for user: {}",user.getName());
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        Rider savedRider = riderRepository.save(rider);
        logger.info("Rider profile created for user: {}",user.getName());
        logger.info("Exiting createNewRiderProfile for user: {}",user.getName());
        return savedRider;
    }

    @Override
    public Rider getCurrentRider() {
//      TODO: Implement Spring Security to get the currently logged-in user
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found"));
    }
}
