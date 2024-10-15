package com.springboot.project.uber.uberapp.dto;

import com.springboot.project.uber.uberapp.entities.Rider;
import com.springboot.project.uber.uberapp.entities.enums.PaymentMethod;
import com.springboot.project.uber.uberapp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {
    private  Long id;
    private Point pickUpLocation;
    private Point dropLocation;
    private LocalDateTime requestTime;
    private Rider rider;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
}
