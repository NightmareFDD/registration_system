package com.engeto.registration_system.exception;

public class PersonIdNotAllowedException extends RuntimeException {
    public PersonIdNotAllowedException(String message) {
        super(message);
    }
}
