package com.kaminsky.walletproject.exceptions;

public class InsufficientFundsException extends IllegalArgumentException {

    public InsufficientFundsException() {
    }

    public InsufficientFundsException(Exception cause) {
        super(cause);
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Exception cause) {
        super(message, cause);
    }
}
