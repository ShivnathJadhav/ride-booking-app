package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.services.DistanceService;
import org.springframework.stereotype.Service;

import java.awt.*;
@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        return 0;
    }
}
