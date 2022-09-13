package com.pms.service;

import java.util.List;

import com.pms.entity.Persona;

public interface PersonaService {
	
	public Persona createPersona(Persona persona);
	
	public List<Persona> getAllPersonas();
	
	public Persona getPersonaById(long idpersona);
	
	public Persona getPersonaByCedula(String cedula);
	
	public Persona updatePersona(long idpersona, Persona persona);
	
	public void deletePersona(long idpersona);
	
}
