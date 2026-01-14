package com.springboot.project.uber.uberapp.repositories;

import com.springboot.project.uber.uberapp.entities.Payment;
import com.springboot.project.uber.uberapp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
