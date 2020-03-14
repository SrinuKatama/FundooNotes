package com.bridgelabz.fundoonotes.serviceImplementation;

/* Author :Srinu */


import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.MailDto;
import com.bridgelabz.fundoonotes.dto.ResetPassword;
import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.model.UserDetails;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.responses.MailResponse;
import com.bridgelabz.fundoonotes.service.UserServe;
import com.bridgelabz.fundoonotes.utility.JWTutil;
import com.bridgelabz.fundoonotes.utility.MailUtility;

@Service
public class UserService implements UserServe {
	private UserDetails userDetails = new UserDetails();

	@Autowired
	private MailDto maildto;
	@Autowired
	MailUtility util;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	MailResponse res;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// private RabbitMqProducer sendMail;

	@Autowired
	private JWTutil jwt;

	@Override
	@Transactional
	public UserDetails register(UserDto user) throws UnsupportedEncodingException {
		UserDetails check = userRepo.findByEmail(user.getUseremail());
		if (check == null) {
			userDetails.setFirstname(user.getFirstname());
			userDetails.setLastname(user.getLastname());
			userDetails.setUseremail(user.getUseremail());
			String epassword = bCryptPasswordEncoder.encode(user.getPassword());
			userDetails.setPassword(epassword);
			userDetails.setMobileno(user.getMobileno());
			userDetails.setLocaldatetime(LocalDateTime.now());
			userDetails.setVerified(true);
			userRepo.save(userDetails);

			// For Token Generation //

			maildto.setEmail(userDetails.getUseremail());
			maildto.setSubject("this mail sent by admin srinivas");
			String maildata = res.response("http://localhost:8082/checking/",
					jwt.jwtToken(userDetails.getId()));
			maildto.setResponse(maildata);
			util.sendMail(maildto);
			return userDetails;
		} else {
			return null;
		}

	}
	
	// for verifying  by id
	@Override
	public UserDetails mailVerification(String token) 
	{
		long verifytoken = 0;
		try {
			verifytoken = jwt.parseJWT(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDetails check=userRepo.findById(verifytoken);
		if(!check.isVerified())
		{
			userRepo.updateIsVeified(verifytoken);
			return userDetails;
		}
		return null;
	}
      // for login purpose
	
	@Override
	public UserDetails login(LoginDto login) throws UnsupportedEncodingException {
		UserDetails getmail = userRepo.findByEmail(login.getEmail());
		if (getmail.getUseremail().equals(login.getEmail())) 
		{
			if(getmail.isVerified())
			{
				boolean pass=bCryptPasswordEncoder.matches(login.getPassword(),getmail.getPassword());
				if(pass)
				{
					maildto.setEmail(getmail.getUseremail());
					String maildata = res.response("http://localhost:8082/checking/",
							jwt.jwtToken(userDetails.getId()));
					maildto.setResponse(maildata);
					maildto.setSubject("this mail is from admin srinu ,you are successfully logged in");
					util.sendMail(maildto);
					
				}
				else
				{
					maildto.setEmail(getmail.getUseremail());
					String maildata = res.response("http://localhost:8082/checking/",
							jwt.jwtToken(userDetails.getId()));
					maildto.setResponse(maildata);
					maildto.setSubject("this mail is from admin srinu ,your login attempt failed	");
					util.sendMail(maildto);
					
				}
			}
			else
			{
				System.out.println("user not verified");
			}
		}

		return null;
	}

	
	// Forget Password
	
	@Override
	public UserDetails forgetPasswod(String email) throws UnsupportedEncodingException 
	{
		UserDetails usermail = userRepo.findByEmail(email);
		if (usermail != null) 
		{
			if (usermail.isVerified())
			{
				maildto.setEmail(usermail.getUseremail());
				String maildata=res.response("http://localhost:8082/checking/",
						jwt.jwtToken(userDetails.getId()));
				maildto.setResponse(maildata);
				maildto.setSubject("this mail is sent by admin srinu");
				util.sendMail(maildto);
				return userDetails;

			}
			else
			{
				System.out.println("your email not verified");
			}
		}
		else
		{
			System.out.println("your email not present in our database");
		}

		return null;
	}

	//Update Password
	@Override
	public boolean updatePassword(ResetPassword password, String token) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long updatepass=jwt.parseJWT(token);
		if(password.getPassword().equals(password.getConfirmPassword()))
		{
			UserDetails id=userRepo.findById(updatepass);
			
			if(id.isVerified())
			{
				id.setPassword(bCryptPasswordEncoder.encode(password.getPassword()));
				userRepo.updatePassword(id.getPassword(), id.getUseremail());
				return true;
			}
			
		}
		else
		{
			System.out.println("plz enter both passwords same");
		}
		
		return false;
	}
	
	
	
     // For fetching all user Details 
	@Override
	public List<UserDetails> getAllusers(String str)
	{
		if(str.equalsIgnoreCase("admin"))
		{
			return userRepo.getUserList();
		}
		else
		{
			System.out.println("plz enter correct string");
			return null;
			
		}
		
	}

	
	
	
	
	
	
	
	
}
