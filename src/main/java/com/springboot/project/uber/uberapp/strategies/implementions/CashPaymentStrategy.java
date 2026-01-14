package com.springboot.project.uber.uberapp.strategies.implementions;

import com.springboot.project.uber.uberapp.entities.Driver;
import com.springboot.project.uber.uberapp.entities.Payment;
import com.springboot.project.uber.uberapp.entities.enums.PaymentStatus;
import com.springboot.project.uber.uberapp.entities.enums.TransactionMethod;
import com.springboot.project.uber.uberapp.repositories.PaymentRepository;
import com.springboot.project.uber.uberapp.services.WalletService;
import com.springboot.project.uber.uberapp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        // Logic for processing Cash on Delivery payment
        System.out.println("Processing Cash on Delivery payment for Payment ID: " + payment.getId());

        Driver driver = payment.getRide().getDriver();

        Double platformCommission = (payment.getAmount() * Double.parseDouble(PaymentStrategy.PLATFORM_COMMISSION_PERCENTAGE)) / 100;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission,null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
