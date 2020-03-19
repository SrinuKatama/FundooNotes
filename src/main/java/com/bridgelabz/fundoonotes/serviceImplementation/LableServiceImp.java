package com.bridgelabz.fundoonotes.serviceImplementation;
/* Author : Srinu */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.LableDto;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.model.UserDetails;
import com.bridgelabz.fundoonotes.repository.LableRepository;
import com.bridgelabz.fundoonotes.repository.NotesRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.LableService;
import com.bridgelabz.fundoonotes.utility.JWTutil;

@Service
public class LableServiceImp implements LableService
{

	
	
	@Autowired
	private LableRepository lablerepo;
	
	@Autowired
	private UserRepository userrepo;
	
	/*
	 * @Autowired private NotesRepository notesrepo;
	 */
	
	@Autowired
	private JWTutil  jwt;
	
	Lable lable=new Lable();

	
	/* For creating the lable*/
	@Override
	public Lable createLable(LableDto LableDto, String token) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails user=userrepo.findById(tokenid);
		if(user!=null && user.isVerified())
		{
			Lable result=lablerepo.findbylablename(LableDto.getLableName(), user.getId());
			if(result==null)
			{
				lable.setLableName(LableDto.getLableName());
				lable.setLableuser(user);
				return lable;
			}
		}
		
		return null;
	}

	
	
	/* For updating the lable*/
	@Override
	public Long updateLable(Long id, String token, String lableName) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails user=userrepo.findById(tokenid);
		Lable result=lablerepo.findbylablename(lableName, id);
		if(result!=null)
		{
			if(user.isVerified())
			{
				String labname=result.getLableName();
				Long labid=result.getLableid();
			    lablerepo.updatelable(labname, labid, id);
				return id;
			}
		}
		
		return null;
	}
	
	/* For deleting the lable*/

	@Override
	public Long deleteLable(Long lableid, String token) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails userresult=userrepo.findById(tokenid);
		Long uid=userresult.getId();
		if(userresult!=null)
		{
			if(userresult.isVerified())
			{
				Lable lableuser=lablerepo.findBylableid(lableid, uid);
				if(lableuser!=null)
				{
					lablerepo.deleteData(lableid, uid);
					return lableid;
				}
			}
		}
		
		
		return null;
	}

	/*For map with the noteTable*/
	
	@Override
	public Lable mapWithNote(String token, Long noteid, String lableName) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tockenid=jwt.parseJWT(token);
		UserDetails user=userrepo.findById(tockenid);
		Long uid=user.getId();
		if(user!=null)
		{
			Lable lableresult=lablerepo.findbylablename(lableName, uid);
			if(lableresult==null)
			{
				lable.setLableuser(user);
				lable.setLableName(lableName);
				Lable insetlable=lablerepo.insertlable(lableName, uid);
				lablerepo.insertmappingdata(insetlable.getLableid(), noteid);
				return insetlable;
			}
			else 
			{
				Lable  labresult=lablerepo.findbylablename(lableName, uid);
				long lid=labresult.getLableid();
				Object mapping=lablerepo.findbylableidandnoteid(lid,noteid);
				if(mapping==null)
				{
					lablerepo.insertmappingdata(lid, noteid);
				}
				
			}
			
		}
		
				return null;
	}

	
	/* For getting the all user lables */
	@Override
	public List<Lable> getAllusers(String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails uid=userrepo.findById(tokenid);
		if(uid!=null)
		{
			Long userid=uid.getId();
			return lablerepo.getAllLables(userid);
		}
		
		return null;
	}
	
	

}
