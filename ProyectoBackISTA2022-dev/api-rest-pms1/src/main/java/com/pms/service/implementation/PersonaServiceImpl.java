package com.pms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.entity.Persona;
import com.pms.exception.ResourceNotFoundException;
import com.pms.repository.PersonaRepository;
import com.pms.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	@Transactional()
	public Persona createPersona(Persona persona) {

		return personaRepository.save(persona);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> getAllPersonas() {

		return personaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Persona getPersonaById(long idpersona) {

		return personaRepository.findById(idpersona)
				.orElseThrow(() -> new ResourceNotFoundException("Persona", "id", idpersona));
	}

	@Override
	@Transactional()
	public Persona updatePersona(long idpersona, Persona persona) {
		Persona p = personaRepository.findById(idpersona)
				.orElseThrow(() -> new ResourceNotFoundException("Persona", "id", idpersona));

		p.setCedula(persona.getCedula());
		p.setNombre(persona.getNombre());
		p.setApellido(persona.getApellido());
		p.setEmail(persona.getEmail());
		p.setTelefono(persona.getTelefono());
		p.setFecha_nacimiento(persona.getFecha_nacimiento());

		return personaRepository.save(p);
	}

	@Override
	@Transactional()
	public void deletePersona(long idpersona) {

		Persona persona = personaRepository.findById(idpersona)
				.orElseThrow(() -> new ResourceNotFoundException("Persona", "id", idpersona));

		personaRepository.delete(persona);

	}

	@Override
	public Persona getPersonaByCedula(String cedula) {

		Persona persona = personaRepository.findByCedula(cedula)
				.orElseThrow(() -> new ResourceNotFoundException("Persona", "cédula", cedula));

		return persona;

	}

}
