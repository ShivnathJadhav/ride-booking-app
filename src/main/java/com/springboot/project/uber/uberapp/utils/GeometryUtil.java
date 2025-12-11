package com.springboot.project.uber.uberapp.utils;

import com.springboot.project.uber.uberapp.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {
    public static Point createPoint(PointDto pointDto){
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        // GeometryFactory is the java's representation of spatial reference system and used to create geometrical objects like Point, LineString, Polygon etc.
        Coordinate coordinate = new Coordinate(pointDto.getCoordinates()[0], pointDto.getCoordinates()[1]);
        //coordinate is a class that represents a point in 2D or 3D space using x, y, and optionally z values.
        Point point = geometryFactory.createPoint(coordinate);
        point.setSRID(4326); // ensure SRID (good to be explicit)
        return point;
    }
}
