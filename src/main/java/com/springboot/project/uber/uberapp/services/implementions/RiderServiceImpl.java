package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.dto.DriverDto;
import com.springboot.project.uber.uberapp.dto.RideDto;
import com.springboot.project.uber.uberapp.dto.RideRequestDto;
import com.springboot.project.uber.uberapp.dto.RiderDto;
import com.springboot.project.uber.uberapp.services.RiderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RiderServiceImpl implements RiderService {
    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
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
}
