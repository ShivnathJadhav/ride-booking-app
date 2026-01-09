package com.springboot.project.uber.uberapp.dto;

import com.springboot.project.uber.uberapp.entities.Rider;
import com.springboot.project.uber.uberapp.entities.enums.PaymentMethod;
import com.springboot.project.uber.uberapp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {
    private  Long id;
    private PointDto pickUpLocation;
    private PointDto dropLocation;
    private PaymentMethod paymentMethod;
    private LocalDateTime requestedTime;
    private Rider rider;
    private  Double fare;
    private RideRequestStatus rideRequestStatus;
}
