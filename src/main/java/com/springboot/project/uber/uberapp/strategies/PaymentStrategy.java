package com.springboot.project.uber.uberapp.strategies;

import com.springboot.project.uber.uberapp.entities.Payment;

public interface PaymentStrategy {
    static final String PLATFORM_COMMISSION_PERCENTAGE = "10";// 10 percent commission
    void processPayment(Payment payment);
}
