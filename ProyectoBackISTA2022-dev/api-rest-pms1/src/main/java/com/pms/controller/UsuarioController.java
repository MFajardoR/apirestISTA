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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.entity.Usuario;
import com.pms.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listUsuarios() {

		return new ResponseEntity<>(usuarioService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable long id) {

		return new ResponseEntity<>(usuarioService.getUsuarioById(id), HttpStatus.OK);
	}

	@GetMapping("/usuario/{username}")
	public ResponseEntity<Usuario> getUsuarioByUsername(@PathVariable String username) {

		return new ResponseEntity<>(usuarioService.getUsuarioByUsername(username), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> saveUsuario(@PathVariable long id,@Valid @RequestBody Usuario usuario) {

		Usuario updatedUser = usuarioService.updateUsuario(id, usuario);
		return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUsuario(@PathVariable long id) {

		usuarioService.deleteUsuario(id);
		return new ResponseEntity<>("Usuario eliminado con id: " + id, HttpStatus.OK);
	}

}
