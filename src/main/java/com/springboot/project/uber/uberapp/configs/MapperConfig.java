package com.springboot.project.uber.uberapp.configs;

import com.springboot.project.uber.uberapp.dto.PointDto;
import com.springboot.project.uber.uberapp.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(PointDto.class, Point.class).setConverter( // Custom converter to map PointDto to Point
                context -> { // Lambda expression defining the conversion logic
                    PointDto source = (PointDto) context.getSource(); // Get the source PointDto object
                    return GeometryUtil.createPoint(source); // Convert PointDto to Point using GeometryUtil
                }
        );
        mapper.typeMap(Point.class, PointDto.class).setConverter(
                context -> {
                    Point source = (Point) context.getSource();
                    double [] coordinates = new double[]{source.getX(), source.getY()};
                    PointDto pointDto = new PointDto();
                    pointDto.setCoordinates(coordinates);
                    return pointDto;
                }
        );

        return mapper;

    }

}
