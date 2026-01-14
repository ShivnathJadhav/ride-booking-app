package com.springboot.project.uber.uberapp.services.implementions;

import com.springboot.project.uber.uberapp.dto.RideDto;
import com.springboot.project.uber.uberapp.dto.WalletTransactionDto;
import com.springboot.project.uber.uberapp.entities.Ride;
import com.springboot.project.uber.uberapp.entities.User;
import com.springboot.project.uber.uberapp.entities.Wallet;
import com.springboot.project.uber.uberapp.entities.WalletTransaction;
import com.springboot.project.uber.uberapp.entities.enums.TransactionMethod;
import com.springboot.project.uber.uberapp.entities.enums.TransactionType;
import com.springboot.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.springboot.project.uber.uberapp.repositories.WalletRepository;
import com.springboot.project.uber.uberapp.services.WalletService;
import com.springboot.project.uber.uberapp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;
    private final ModelMapper modelMapper;

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet withdrowMoneyFromWallet(Long userId, Double amount) {
        return null;
    }

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(()-> new ResourceNotFoundException("Wallet not found with id: " + walletId));
    }


    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        if(wallet.getBalance() < amount){
            throw new RuntimeException("Insufficient balance in wallet");
        }
        wallet.setBalance(wallet.getBalance() - amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();
        //walletTransactionService.createNewWalletTransaction(walletTransaction);
        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(()-> new ResourceNotFoundException("Wallet not found for user id: " + user.getId()));
    }
}
