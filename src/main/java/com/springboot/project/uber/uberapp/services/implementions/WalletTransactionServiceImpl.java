package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.dto.WalletTransactionDto;
import com.springboot.project.uber.uberapp.entities.WalletTransaction;
import com.springboot.project.uber.uberapp.repositories.WalletTransactionRepository;
import com.springboot.project.uber.uberapp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
