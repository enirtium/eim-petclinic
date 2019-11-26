package com.eim.eimpetclinic.vet;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VeteniaryList {
	private List<Veterinary> veteniaries;

	@XmlElement
	public List<Veterinary> getVeteniaries() {
		if(null == veteniaries){
			veteniaries = new ArrayList<Veterinary>();
		}
		return veteniaries;
	}

	public void setVeteniaries(List<Veterinary> veteniaries) {
		this.veteniaries = veteniaries;
	}
}
