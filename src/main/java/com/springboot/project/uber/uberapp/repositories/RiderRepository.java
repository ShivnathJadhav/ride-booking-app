package com.springboot.project.uber.uberapp.repositories;

import com.springboot.project.uber.uberapp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long> {
}
