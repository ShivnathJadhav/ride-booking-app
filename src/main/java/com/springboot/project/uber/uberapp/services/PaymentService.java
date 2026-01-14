package com.springboot.project.uber.uberapp.services;

import com.springboot.project.uber.uberapp.entities.Payment;
import com.springboot.project.uber.uberapp.entities.Ride;
import com.springboot.project.uber.uberapp.entities.enums.PaymentStatus;

public interface PaymentService {
    Payment createNewPayment(Ride ride);
    void processPayment(Ride ride);
    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
