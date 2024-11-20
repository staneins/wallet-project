package com.kaminsky.walletproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletResponseDTO {
    @NotNull
    private UUID walletId;
    @NotNull
    @Positive
    private BigDecimal amount;

    public WalletResponseDTO(UUID walletId, BigDecimal amount) {
        this.walletId = walletId;
        this.amount = amount;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
