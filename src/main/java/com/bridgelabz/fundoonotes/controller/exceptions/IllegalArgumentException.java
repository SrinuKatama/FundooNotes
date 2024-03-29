package com.bridgelabz.fundoonotes.controller.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

public class IllegalArgumentException extends Exception
{
	List<ObjectError> errors;

    public static IllegalArgumentException createWith(List<ObjectError> errors) {
        return new IllegalArgumentException(errors);
    }

    private IllegalArgumentException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

}
