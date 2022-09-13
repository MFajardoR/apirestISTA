package com.pms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pms.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	public Optional<Persona> findByCedula(String cedula);

}
