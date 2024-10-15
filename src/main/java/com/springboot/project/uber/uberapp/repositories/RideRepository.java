package com.springboot.project.uber.uberapp.repositories;

import com.springboot.project.uber.uberapp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride,Long> {
}
