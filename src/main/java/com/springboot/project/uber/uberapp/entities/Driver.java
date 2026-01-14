package com.springboot.project.uber.uberapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name ="user_id", unique = true)
    private User user; // One-to-one relationship with User entity, each driver is a user

    private Double rating; // Rating of the driver, can be used to determine the driver's reliability or behavior during rides

    private boolean available; // Availability status of the driver, indicating whether the driver is currently available for rides

    @Column(columnDefinition = "Geometry(Point, 4326)") // Storing the current location of the driver as a Point geometry type, using SRID 4326 for geographic coordinates
    // This column will store the driver's current location in a spatial format, allowing for efficient spatial queries
    private Point currentLocation; // Current location of the driver, stored as a Point geometry type for spatial queries
}
