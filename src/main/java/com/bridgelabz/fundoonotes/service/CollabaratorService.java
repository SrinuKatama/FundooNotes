package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.model.Collabarator;

public interface CollabaratorService 
{
	Collabarator saveData(String collabaratormail,Long noteid);
    boolean deleteData(String collabaratormail);
	List<Collabarator>getListofCollabarators(Long noteid);

}
