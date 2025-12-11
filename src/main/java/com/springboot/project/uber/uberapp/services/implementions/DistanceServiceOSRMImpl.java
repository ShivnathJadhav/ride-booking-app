package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

// This service will user OSRM API (open source routing machine) to calculate distance between two points.
@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
//TODO        call the thrid party OSRM API to calculate distance between src and dest points
        return 0;
    }
}
