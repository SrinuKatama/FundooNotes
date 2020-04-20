package com.bridgelabz.fundoonotes.controller.exceptions;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.utility.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {

	// exception classes
	@ExceptionHandler({ JWTVerificationException.class, IllegalArgumentException.class,
			UnsupportedEncodingException.class })

	public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();

		if (ex instanceof JWTVerificationException) {
			HttpStatus status = HttpStatus.NOT_FOUND;
			JWTVerificationException jwtv = (JWTVerificationException) ex;
			return handleJWTVerificationException((JWTVerificationException) ex, headers, status, request);

		} else if (ex instanceof IllegalArgumentException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			IllegalArgumentException ile = (IllegalArgumentException) ex;
			return handleIllegalArgumentException((IllegalArgumentException) ex, headers, status, request);

		} else {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			UnsupportedEncodingException use = (UnsupportedEncodingException) ex;
			return handleUnsupportedEncodingException((UnsupportedEncodingException) ex, headers, status, request);

		}

	}

	// custom exceptions jwt

	protected ResponseEntity<ApiError> handleJWTVerificationException(JWTVerificationException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
		return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
	}

	// custom exception for illegalarguent

	protected ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
		return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);

	}

	// custom exception for UnsupportedEncodingException

	protected ResponseEntity<ApiError> handleUnsupportedEncodingException(UnsupportedEncodingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
		return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
	}

	protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, @Nullable ApiError body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}

		return new ResponseEntity<>(body, headers, status);
	}

}
