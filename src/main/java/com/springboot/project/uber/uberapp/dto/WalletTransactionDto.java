package com.springboot.project.uber.uberapp.dto;

import com.springboot.project.uber.uberapp.entities.enums.TransactionMethod;
import com.springboot.project.uber.uberapp.entities.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {
    private Long id;

    private Double balance;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDto rideDto;

    private String transactionId;

    private WalletDto walletDto;

    private LocalDateTime timeStamp;

}
