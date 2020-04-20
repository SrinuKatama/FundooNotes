package com.bridgelabz.fundoonotes.controller.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

public class UnsupportedEncodingException 
{
	List<ObjectError> errors;

    public static UnsupportedEncodingException createWith(List<ObjectError> errors) {
        return new UnsupportedEncodingException(errors);
    }

    private UnsupportedEncodingException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

}
