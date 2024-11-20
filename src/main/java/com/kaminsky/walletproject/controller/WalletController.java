package com.kaminsky.walletproject.controller;

import com.kaminsky.walletproject.dto.WalletRequestDTO;
import com.kaminsky.walletproject.dto.WalletResponseDTO;
import com.kaminsky.walletproject.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping(value = WalletController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletController {
    public static final String URL = "/api/v1";

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping(value = "/wallets/{WALLET_UUID}")
    public WalletResponseDTO get(@PathVariable UUID WALLET_UUID) {
    BigDecimal amount = walletService.getWalletAmount(WALLET_UUID);
    return new WalletResponseDTO(WALLET_UUID, amount);
    }

    @PostMapping(value = "/wallet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@Valid @RequestBody WalletRequestDTO walletDTO) {
        walletService.updateAmount(
                walletDTO.getWalletId(),
                walletDTO.getAmount(),
                walletDTO.getOperationType()
        );
    }
}
