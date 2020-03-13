package com.bridgelabz.fundoonotes.serviceImplementation;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.MailDto;
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
		if(check.isVerified())
		{
			userRepo.updateIsVeified(verifytoken);
			return userDetails;
		}
		return null;
	}

}
