package com.bridgelabz.fundoonotes.serviceImplementation;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.model.ProfilePic;
import com.bridgelabz.fundoonotes.model.UserDetails;
import com.bridgelabz.fundoonotes.repository.ProfilePicrepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.ProfilepicService;
import com.bridgelabz.fundoonotes.utility.JWTutil;

@Service
public class ProfilepicServiceImp implements ProfilepicService {

	@Autowired
	private AmazonS3 amazonS3;

	private String bucketName;

	@Autowired
	private JWTutil jwt;

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private ProfilePicrepository profilerepo;

	/* Method for Uploading the Profile Picture */

	@Override
	public ProfilePic uploadProfilePic(MultipartFile multiFile, String fileName, String token)
			throws JWTVerificationException, IllegalArgumentException, Exception
	{
		String getfile = multiFile.getOriginalFilename();

		long tokenid = jwt.parseJWT(token);
		UserDetails userdetails = userrepo.findById(tokenid);
		long id = userdetails.getId();

		if (userdetails != null) {
			
			// creating the file in the server temporarily
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multiFile.getBytes());
			fos.close();
			

			ProfilePic propic = new ProfilePic();
			propic.setProfilename(getfile);
			propic.setUserdetails(userdetails);

			profilerepo.saveData(propic.getProfilename(), id);
			Long pid1=profilerepo.getPicByUser(id);
			PutObjectRequest objectRequest = new PutObjectRequest(bucketName, pid1 + getfile, file);
			amazonS3.putObject(objectRequest);			
			
			// delete the file from the server
			file.delete();
			return propic;

		}

		return null;
	}
	
	/*method for deletig the profile*/

	@Override
	public boolean deleteFileName(Long profileId) 
	{
		ProfilePic profile=profilerepo.searchById(profileId);
		if(profile!=null)
		{
			String filename=profile.getProfilename();
			amazonS3.deleteObject(new DeleteObjectRequest(bucketName, profileId+filename));
			profilerepo.deleteData(profileId);
			return true;
		}
		
		return false;
	}

}
