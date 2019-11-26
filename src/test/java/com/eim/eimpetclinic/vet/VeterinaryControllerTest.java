package com.eim.eimpetclinic.vet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VeterinaryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	//@Test
	public void getVeteniaries() throws Exception{
		 mockMvc.perform(get("/vets")).andDo(print());
	}
}
