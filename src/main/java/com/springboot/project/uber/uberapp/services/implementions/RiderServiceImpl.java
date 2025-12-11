package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.dto.DriverDto;
import com.springboot.project.uber.uberapp.dto.RideDto;
import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.dto.RiderDto;
import com.springboot.project.uber.uberapp.entities.RideRequest;
import com.springboot.project.uber.uberapp.entities.Rider;
import com.springboot.project.uber.uberapp.entities.User;
import com.springboot.project.uber.uberapp.entities.enums.RideRequestStatus;
import com.springboot.project.uber.uberapp.repositories.RideRequestRepository;
import com.springboot.project.uber.uberapp.repositories.RiderRepository;
import com.springboot.project.uber.uberapp.services.RiderService;
import com.springboot.project.uber.uberapp.strategies.DriverMatchingStrategy;
import com.springboot.project.uber.uberapp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(RiderServiceImpl.class);
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        logger.info("Rider requested a ride from {} to {} using payment method {}",
                rideRequest.getPickUpLocation(), rideRequest.getDropLocation(), rideRequest.getPaymentMethod());

        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        Double estimatedFare = rideFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(estimatedFare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        driverMatchingStrategy.findMatchingDriver(rideRequest);
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRiderProfile(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }
}
