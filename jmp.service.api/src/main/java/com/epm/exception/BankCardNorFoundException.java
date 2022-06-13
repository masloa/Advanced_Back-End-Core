package com.epm.exception;

public class BankCardNorFoundException extends RuntimeException {

    public BankCardNorFoundException() {
        super("Bank card not found!");
    }

    public BankCardNorFoundException(String message) {
        super(message);
    }
}
