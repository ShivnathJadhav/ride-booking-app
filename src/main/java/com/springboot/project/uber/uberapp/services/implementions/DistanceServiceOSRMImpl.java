package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

// This service will user OSRM API (open source routing machine) to calculate distance between two points.
@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    Logger logger = LoggerFactory.getLogger(DistanceServiceOSRMImpl.class);
    private static final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";
    @Override
    public double calculateDistance(Point src, Point dest) {
        logger.info("Calculating distance between {} and {} using OSRM API", src, dest);
        try {
            String uri = src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
            OSRMResponseDto osrmResponseDto = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL) // setting up base URL
                    .build()    // building the client
                    .get()  // making GET request to the API to fetch distance. it will append the uri to the base URL
                    .uri(uri)   // setting up the URI with source and destination coordinates
                    .retrieve() // retrieving the response
                    .body(OSRMResponseDto.class); // mapping the response to OSRMResponseDto class

            if(osrmResponseDto.getRoutes().isEmpty()){
                logger.error("No routes found between the given points");
                throw new RuntimeException("No routes found between the given points");
            }
            logger.info("Successfully fetched distance from OSRM API");
            return osrmResponseDto.getRoutes().get(0).getDistance()/1000.0; // converting meters to kilometers

        } catch (Exception e){
            logger.error("Error while fetching distance from OSRM API: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch distance from OSRM API "+e.getMessage());
        }
    }
}

@Data
class OSRMResponseDto{
    // Define required fields and methods to map OSRM API response
    private List<OSRMRoutes> routes;
}

@Data
class OSRMRoutes {
    private double duration;
    private double distance;
}
