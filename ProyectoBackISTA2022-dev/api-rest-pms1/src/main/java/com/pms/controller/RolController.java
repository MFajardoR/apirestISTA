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

import com.pms.entity.Rol;
import com.pms.service.RolService;

@RestController
@RequestMapping("/api/roles")
public class RolController {
	
	@Autowired
	private RolService rolService;
	
	@GetMapping
	public ResponseEntity<List<Rol>> listrols() {

		return new ResponseEntity<>(rolService.getAllRoles(), HttpStatus.OK);
	}

	@GetMapping("/{idrol}")
	public ResponseEntity<Rol> getrolById(@PathVariable long idrol) {

		return new ResponseEntity<>(rolService.getRolById(idrol), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Rol> saverol(@Valid @RequestBody Rol rol) {

		return new ResponseEntity<>(rolService.createRol(rol), HttpStatus.CREATED);

	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{idrol}")
	public ResponseEntity<Rol> saverol(@PathVariable long idrol,@Valid @RequestBody Rol rol) {

		Rol updatedRol = rolService.updateRol(idrol, rol);
		return new ResponseEntity<>(updatedRol, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{idrol}")
	public ResponseEntity<String> deleterol(@PathVariable long idrol) {

		rolService.deleteRol(idrol);
		return new ResponseEntity<>("rol eliminado con id: " + idrol, HttpStatus.OK);
	}

}
