package com.springboot.project.uber.uberapp.strategies.implementions;

import com.springboot.project.uber.uberapp.entities.Driver;
import com.springboot.project.uber.uberapp.entities.Payment;
import com.springboot.project.uber.uberapp.entities.Rider;
import com.springboot.project.uber.uberapp.entities.enums.PaymentStatus;
import com.springboot.project.uber.uberapp.entities.enums.TransactionMethod;
import com.springboot.project.uber.uberapp.repositories.PaymentRepository;
import com.springboot.project.uber.uberapp.services.PaymentService;
import com.springboot.project.uber.uberapp.services.WalletService;
import com.springboot.project.uber.uberapp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        // Logic for processing Wallet payment
        // Deducting the amount from rider's wallet and updating the driver's wallet with deducting platform commission
        System.out.println("Processing Wallet payment for Payment ID: " + payment.getId());
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        Double platformCommission = (payment.getAmount() * Double.parseDouble(PaymentStrategy.PLATFORM_COMMISSION_PERCENTAGE)) / 100;
        // Deduct amount from rider's wallet
        walletService.deductMoneyFromWallet(rider.getUser(),payment.getAmount(),null, payment.getRide(), TransactionMethod.RIDE);
        // Credit amount to driver's wallet after deducting platform commission
        double amountToCreditforDriver = payment.getAmount() - platformCommission;
        // Credit amount to driver's wallet
        walletService.addMoneyToWallet(driver.getUser(), amountToCreditforDriver, null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
