package com.kaminsky.walletproject.exceptions;

public class InsufficientFundsExceptions extends IllegalArgumentException {

    public InsufficientFundsExceptions() {
    }

    public InsufficientFundsExceptions(Exception cause) {
        super(cause);
    }

    public InsufficientFundsExceptions(String message) {
        super(message);
    }

    public InsufficientFundsExceptions(String message, Exception cause) {
        super(message, cause);
    }
}
