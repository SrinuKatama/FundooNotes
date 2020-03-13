package com.bridgelabz.fundoonotes.responses;

public class Responses
{
	private String message;
	private int statusCode;
	private Object details;
	public String getMessage() {
		return message;
	}
	public Responses(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	public Responses(String message, int statusCode, Object details) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.details = details;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Object getDetails() {
		return details;
	}
	public void setDetails(Object details) {
		this.details = details;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
