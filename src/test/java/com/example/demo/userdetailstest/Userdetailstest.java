package com.example.demo.userdetailstest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgelabz.fundoonotes.model.UserDetails;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.serviceImplementation.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Userdetailstest 
{
	
	@MockBean
	UserRepository userrepo;
	
	@Autowired
	UserService userserve;
	
	
	@Test
	public void getUserstest()
	{
		when(userrepo.getUserList()).thenReturn(Stream.of(new UserDetails(1,"srinu","b","srinu@123","123","95425")).
				collect(Collectors.toList()));
		assertEquals(1,userserve.getAllusers("admin").size());
		
	}

}
