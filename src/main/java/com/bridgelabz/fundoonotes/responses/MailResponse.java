package com.bridgelabz.fundoonotes.responses;

import org.springframework.stereotype.Component;

@Component
public class MailResponse 
{
public String response(String url,String token) {
	return url+"/"+token;
}
}
