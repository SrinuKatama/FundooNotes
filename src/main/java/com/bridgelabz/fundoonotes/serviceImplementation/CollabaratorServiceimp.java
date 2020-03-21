package com.bridgelabz.fundoonotes.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.model.Collabarator;
import com.bridgelabz.fundoonotes.model.Notes;
import com.bridgelabz.fundoonotes.repository.Collabaratorrepository;
import com.bridgelabz.fundoonotes.repository.NotesRepository;
import com.bridgelabz.fundoonotes.service.CollabaratorService;

@Service
public class CollabaratorServiceimp implements CollabaratorService 
{
	
	@Autowired
	NotesRepository notesrepo;
	
	@Autowired
	Collabaratorrepository collbrepo;
	
	Collabarator collmodel=new Collabarator();

	@Override
	public Collabarator saveData(String collabaratormail, Long noteid) {
		Notes noteresult = notesrepo.findByNoteId(noteid);
		if (noteresult != null) {
			Collabarator collresult = collbrepo.findbymail(collabaratormail, noteid);
			if (collresult == null) {
				collmodel.setCollabaratormail(collabaratormail);
				collmodel.setNotes(noteresult);
				collbrepo.insertData(collmodel.getCollabaratormail(), noteid);
				return collmodel;
			}
		}

		return null;

	}

	@Override
	public boolean deleteData(String collabaratormail)
	{
		Collabarator collresult=collbrepo.findbycollmail(collabaratormail);
		if(collresult!=null)
		{
			long cid1=collresult.getCid();
			collbrepo.deleteData(cid1);
			return true;
			
		}
		return false;
	}

	@Override
	public List<Collabarator> getListofCollabarators(Long noteid) 
	{
		Notes nid=notesrepo.findByNoteId(noteid);
		if(nid!=null)
		{
			return collbrepo.getusers(noteid);
		
		}
		return null;
	}
	
	

}
