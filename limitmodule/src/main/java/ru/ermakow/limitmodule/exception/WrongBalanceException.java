package ru.ermakow.limitmodule.exception;

public class WrongBalanceException extends RuntimeException {

    public WrongBalanceException(String message) {
        super(message);
    }
}
