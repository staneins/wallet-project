package com.kaminsky.walletproject.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class WalletNotFoundException extends EntityNotFoundException {

    public WalletNotFoundException() {
    }

    public WalletNotFoundException(Exception cause) {
        super(cause);
    }

    public WalletNotFoundException(String message) {
        super(message);
    }

    public WalletNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
