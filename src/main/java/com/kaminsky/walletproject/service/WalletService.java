package com.kaminsky.walletproject.service;

import com.kaminsky.walletproject.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;


    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Long getWalletAmount(Long walletId) {
        return walletRepository.getById(walletId).getAmount();
    }

    public void updateAmount(Long walletId, Long amount) {
        walletRepository.getById(walletId).setAmount(amount);
    }
}
