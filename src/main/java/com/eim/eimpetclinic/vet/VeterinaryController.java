package com.eim.eimpetclinic.vet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class VeterinaryController {
	private static final Logger logger = LoggerFactory.getLogger(VeterinaryController.class);
	
	@Autowired
    VeterinaryRepository service;
	
	@GetMapping({ "/vets" })
	public @ResponseBody VeteniaryList getVeteniaries(){
		logger.info("In Vet Controller");
		VeteniaryList returnval = new VeteniaryList();
		Veterinary vet = new Veterinary();
		//vet.setId(7);
		vet.setFirstName("john");
		vet.setLastName("doe");
		//vet = service.save(vet);
		vet = new Veterinary();
		//vet.setId(7);
		vet.setFirstName("johnny");
		vet.setLastName("doey");
		//vet = service.save(vet);
		returnval.getVeteniaries().addAll(service.findAll());
		return returnval;
	}
}
