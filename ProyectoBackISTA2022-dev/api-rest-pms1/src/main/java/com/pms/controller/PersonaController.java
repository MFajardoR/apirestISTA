package com.pms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.entity.Persona;
import com.pms.service.PersonaService;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@GetMapping
	public ResponseEntity<List<Persona>> listPersonas() {

		return new ResponseEntity<>(personaService.getAllPersonas(), HttpStatus.OK);
	}

	@GetMapping("/{idpersona}")
	public ResponseEntity<Persona> getPersonaById(@PathVariable long idpersona) {

		return new ResponseEntity<>(personaService.getPersonaById(idpersona), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Persona> savePersona(@Valid @RequestBody Persona persona) {

		return new ResponseEntity<>(personaService.createPersona(persona), HttpStatus.CREATED);

	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{idpersona}")
	public ResponseEntity<Persona> savePersona(@PathVariable long idpersona,@Valid  @RequestBody Persona persona) {

		Persona updatedPerson = personaService.updatePersona(idpersona, persona);
		return new ResponseEntity<>(updatedPerson, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{idpersona}")
	public ResponseEntity<String> deletePersona(@PathVariable long idpersona) {

		personaService.deletePersona(idpersona);
		return new ResponseEntity<>("Persona eliminada con id: " + idpersona, HttpStatus.OK);
	}

}
