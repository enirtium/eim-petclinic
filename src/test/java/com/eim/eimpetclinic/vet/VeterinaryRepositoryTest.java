package com.eim.eimpetclinic.vet;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
@Transactional
public class VeterinaryRepositoryTest {

	@Autowired
	VeterinaryRepository repo;
	
	@Test
	public void findAll(){
		Collection<Veterinary> all = repo.findAll();
		for (Iterator iterator = all.iterator(); iterator.hasNext();) {
			Veterinary veterinary = (Veterinary) iterator.next();
			System.out.println(veterinary.getFirstName());
		}
		assertEquals(6, all.size());
	}
	
	@Test
	public void create(){
		Veterinary vet = new Veterinary();
		//vet.setId(7);
		vet.setFirstName("john");
		vet.setLastName("doe");
		vet = repo.save(vet);
		vet = repo.findById(vet.getId()).get();
		assertEquals("john",vet.getFirstName());
	}
}
