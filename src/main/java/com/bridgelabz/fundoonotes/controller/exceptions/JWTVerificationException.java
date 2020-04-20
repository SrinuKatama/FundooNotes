package com.bridgelabz.fundoonotes.controller.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

public class JWTVerificationException extends Exception
{
	List<ObjectError> errors;

    public static JWTVerificationException createWith(List<ObjectError> errors) {
        return new JWTVerificationException(errors);
    }

    private JWTVerificationException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

}
