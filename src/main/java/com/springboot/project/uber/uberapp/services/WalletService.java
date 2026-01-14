package com.springboot.project.uber.uberapp.services;

import com.springboot.project.uber.uberapp.entities.Ride;
import com.springboot.project.uber.uberapp.entities.User;
import com.springboot.project.uber.uberapp.entities.Wallet;
import com.springboot.project.uber.uberapp.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet createNewWallet(User user);

    Wallet withdrowMoneyFromWallet(Long userId, Double amount);

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet findWalletById(Long walletId);

    Wallet findByUser(User user);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
}
