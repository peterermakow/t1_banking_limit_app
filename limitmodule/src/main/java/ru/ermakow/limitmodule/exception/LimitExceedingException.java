package ru.ermakow.limitmodule.exception;

public class LimitExceedingException extends RuntimeException{

    public LimitExceedingException(String message) {
        super(message);
    }
}
