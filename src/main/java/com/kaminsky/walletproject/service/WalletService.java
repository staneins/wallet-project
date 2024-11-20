package com.kaminsky.walletproject.service;

import com.kaminsky.walletproject.entity.Wallet;
import com.kaminsky.walletproject.exceptions.InsufficientFundsExceptions;
import com.kaminsky.walletproject.exceptions.WalletNotFoundException;
import com.kaminsky.walletproject.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository walletRepository;


    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public BigDecimal getWalletAmount(UUID walletId) {
        return walletRepository.getReferenceById(walletId).getAmount();
    }

    @Transactional
    public void updateAmount(UUID walletId, BigDecimal amount, Wallet.OperationType operationType) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Кошелек не найден"));

        if (operationType == Wallet.OperationType.DEPOSIT) {
            wallet.setAmount(wallet.getAmount().add(amount));
        } else if (operationType == Wallet.OperationType.WITHDRAW) {
            if (wallet.getAmount().compareTo(amount) < 0) {
                throw new InsufficientFundsExceptions("Недостаточно средств");
            }
            wallet.setAmount(wallet.getAmount().subtract(amount));
        }
        walletRepository.save(wallet);
    }
}
