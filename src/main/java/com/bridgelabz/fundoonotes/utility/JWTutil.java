package com.bridgelabz.fundoonotes.utility;

import java.io.UnsupportedEncodingException;

// Author :Srinu


import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTutil
{private static final String SECRET = "45144523456";

/* Method to generate the token for the particular userId */
public String jwtToken(long Id) throws UnsupportedEncodingException {
	String token = null;
	try {
		token = JWT.create().withClaim("id", Id).sign(Algorithm.HMAC512(SECRET));
	} catch (IllegalArgumentException | JWTCreationException e) {

		e.printStackTrace();
	}
	return token;
}

/* Method to decode the token to id */
public long parseJWT(String jwt) throws JWTVerificationException, IllegalArgumentException, Exception {
	long Id = (long) 0;
	if (jwt != null) {
		Id = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwt).getClaim("id").asLong();
	}
	return Id;
}

}
