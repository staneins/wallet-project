package com.kaminsky.walletproject.controller;

import com.kaminsky.walletproject.entity.Wallet;
import com.kaminsky.walletproject.service.WalletService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = WalletController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletController {
    public static final String URL = "/api/v1";

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping(value = "/wallets/{WALLET_UUID}")
    public Long get(@PathVariable Long WALLET_UUID) {
    return walletService.getWalletAmount(WALLET_UUID);
    }

    @PostMapping(value = "/wallet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody Wallet wallet) {
        walletService.updateAmount(wallet.getWalletId(), wallet.getAmount());
    }
}
