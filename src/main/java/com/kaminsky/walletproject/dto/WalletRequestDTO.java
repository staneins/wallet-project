package com.kaminsky.walletproject.dto;

import com.kaminsky.walletproject.entity.Wallet;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletRequestDTO {
    @NotNull
    private UUID walletId;
    @NotNull
    private Wallet.OperationType operationType;
    @NotNull
    @Positive
    private BigDecimal amount;

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public Wallet.OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(Wallet.OperationType operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
