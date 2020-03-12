package com.bridgelabz.fundoonotes.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.MailDto;
import com.bridgelabz.fundoonotes.dto.ResetPassword;
import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.model.UserDetails;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.UserServe;
import com.bridgelabz.fundoonotes.utility.JWTutil;

@Service
public class UserService implements UserServe
{
	private UserDetails user1=new UserDetails();
	
	@Autowired
	private MailDto maildto;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//private RabbitMqProducer sendMail;
	
	@Autowired
	private JWTutil jwt;



	@Override
	public UserDetails save(UserDto user)
	{
		return user1;
	
		
	}




}
