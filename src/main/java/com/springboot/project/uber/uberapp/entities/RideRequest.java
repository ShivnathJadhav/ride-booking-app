package com.springboot.project.uber.uberapp.entities;

import com.springboot.project.uber.uberapp.entities.enums.PaymentMethod;
import com.springboot.project.uber.uberapp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickUpLocation; // Column to store the pickup location as a Point geometry type with SRID 4326 (WGS 84)

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropLocation; // Column to store the drop-off location as a Point geometry type with SRID 4326 (WGS 84)

    @CreationTimestamp // it will fill the requestTimme automatically
    private LocalDateTime requestTime;  // Timestamp when the ride request was created, automatically filled by Hibernate

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider; // Many-to-one relationship with Rider entity, each ride request is made by a rider

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // CASH, CARD, UPI

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus; // PENDING, CANCELLED, CONFIRMED
}
