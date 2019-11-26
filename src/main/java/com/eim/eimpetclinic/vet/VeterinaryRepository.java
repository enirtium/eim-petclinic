package com.eim.eimpetclinic.vet;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface VeterinaryRepository extends CrudRepository<Veterinary, Integer> {

    @Transactional(readOnly = true)
    //@Cacheable("vets")
    Collection<Veterinary> findAll() throws DataAccessException;
}
