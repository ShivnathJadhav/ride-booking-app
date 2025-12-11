package com.springboot.project.uber.uberapp.entities;

import com.springboot.project.uber.uberapp.entities.enums.PaymentMethod;
import com.springboot.project.uber.uberapp.entities.enums.RideRequestStatus;
import com.springboot.project.uber.uberapp.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickUpLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropLocation; // Column to store the pickup and drop-off locations as Point geometry types with SRID 4326 (WGS 84)

    @CreationTimestamp // it will fill the requestTimme automatically
    private LocalDateTime createdTime; // Timestamp when the driver accepted the ride request, automatically filled by Hibernate

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider; // Many-to-one relationship with Rider entity because each ride is requested by a rider

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver; // Many-to-one relationship with Driver entity, each ride is assigned to a driver

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus; //     CANCELLED, CONFIRMED, ENDED, ONGOING

    private Double fare; // Fare for the ride, can be calculated based on distance, time, and other factors

    private LocalDateTime startedAt; // Timestamp when the ride started, can be used to calculate the duration of the ride

    private LocalDateTime endedAt; // Timestamp when the ride ended, can be used to calculate the duration of the ride and fare calculation
}
