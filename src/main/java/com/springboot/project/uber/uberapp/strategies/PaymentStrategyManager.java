package com.springboot.project.uber.uberapp.strategies;

import com.springboot.project.uber.uberapp.entities.enums.PaymentMethod;
import com.springboot.project.uber.uberapp.strategies.implementions.CashPaymentStrategy;
import com.springboot.project.uber.uberapp.strategies.implementions.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
            default -> throw new RuntimeException("Unsupported payment method: " + paymentMethod);
        };
    }

}
