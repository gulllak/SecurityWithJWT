package ru.evgenii.SecurityWithJWT.exception;

public class UsernameAlreadyExist extends RuntimeException {
    public UsernameAlreadyExist(String message) {
        super(message);
    }
}
