package com.springboot.project.uber.uberapp.services;

import com.springboot.project.uber.uberapp.dto.WalletTransactionDto;
import com.springboot.project.uber.uberapp.entities.WalletTransaction;

public interface WalletTransactionService {
    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
