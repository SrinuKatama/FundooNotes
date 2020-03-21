package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.model.Collabarator;
import com.bridgelabz.fundoonotes.responses.Responses;
import com.bridgelabz.fundoonotes.service.CollabaratorService;

import io.swagger.annotations.ApiOperation;

@RestController
public class CollabaratorController {
	@Autowired
	private CollabaratorService coolbserve;

	/* JPA for collabarator creation */

	@PostMapping("/collabaratecreate")
	@ApiOperation(value = "for creatig the collabarator")
	public ResponseEntity<Responses> saveData(@RequestParam String collabaratormail, @RequestParam Long noteid) {
		Collabarator result = coolbserve.saveData(collabaratormail, noteid);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responses("collabaration is done", 200, result));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Responses("unable to collabarate", 400, result));
		}

	}

	/* API for delete the collabarator */

	@DeleteMapping("/deletecollabarator")
	@ApiOperation(value = "for deletig the collabarator")
	public ResponseEntity<Responses> deleteData(@RequestParam String collabaratormail) {
		boolean result = coolbserve.deleteData(collabaratormail);
		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responses("collabaration is done", 200, result));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Responses(" unable to delete the collabaration", 400, result));
		}

	}

	/* API for get all collabarators */

	@GetMapping("/getting the users")
	@ApiOperation(value = "for getting the all collabarators")
	public ResponseEntity<Responses> getListofCollabarators(@RequestParam Long noteid) {
		List<Collabarator> result = coolbserve.getListofCollabarators(noteid);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Responses("getting all collabarators ", 200, result));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Responses(" unable to get all collabarators", 400, result));
		}

	}

}
