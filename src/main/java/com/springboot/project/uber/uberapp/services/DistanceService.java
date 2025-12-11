package com.springboot.project.uber.uberapp.services;


import org.locationtech.jts.geom.Point;

// This service is responsible for calculating the distance between two geographical points.
public interface DistanceService {
    double calculateDistance(Point soutce, Point destination);
}
