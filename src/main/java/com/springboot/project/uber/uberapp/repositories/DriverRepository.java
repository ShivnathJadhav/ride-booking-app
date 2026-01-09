package com.springboot.project.uber.uberapp.repositories;

import com.springboot.project.uber.uberapp.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Query(value = "SELECT d.*,ST_Distance(d.current_location :pickUpLocation) AS distance " +
            "FROM driver as d " +
            "WHERE available = true AND ST_DWithin(d.current_location, :pickUpLocation, 5000) " +
            "ORDER BY distance ASC " +
            "LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickUpLocation);

    @Query(value = "SELECT d.* " +
            "FROM driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickUpLocation, 5000) " +
            "ORDER BY d.rating DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Driver> findTopRatedNearByDrivers(Point pickUpLocation);
}
