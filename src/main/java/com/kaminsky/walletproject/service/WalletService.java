package com.kaminsky.walletproject.service;

import com.kaminsky.walletproject.entity.Wallet;
import com.kaminsky.walletproject.exceptions.InsufficientFundsException;
import com.kaminsky.walletproject.exceptions.WalletNotFoundException;
import com.kaminsky.walletproject.repository.WalletRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {
    @PersistenceContext
    private EntityManager entityManager;

    private final WalletRepository walletRepository;


    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public BigDecimal getWalletAmount(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Кошелек не найден"));

        return wallet.getAmount();
    }

    @Transactional
    public void updateAmount(UUID walletId, BigDecimal amount, Wallet.OperationType operationType) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Кошелек не найден"));


        if (operationType == Wallet.OperationType.DEPOSIT) {
            wallet.setAmount(wallet.getAmount().add(amount));
        } else if (operationType == Wallet.OperationType.WITHDRAW) {
            if (wallet.getAmount().compareTo(amount) < 0) {
                throw new InsufficientFundsException("Недостаточно средств");
            }
            wallet.setAmount(wallet.getAmount().subtract(amount));
        }
        walletRepository.save(wallet);
    }


}
