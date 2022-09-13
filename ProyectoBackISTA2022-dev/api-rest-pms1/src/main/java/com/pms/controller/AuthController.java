package com.pms.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.dto.LoginDTO;
import com.pms.dto.SignUpDTO;
import com.pms.dto.security.JwtAuthResonseDTO;
import com.pms.entity.Persona;
import com.pms.entity.Rol;
import com.pms.entity.Usuario;
import com.pms.repository.RolRepository;
import com.pms.security.JwtTokenProvider;
import com.pms.service.PersonaService;
import com.pms.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	// ---

	@Autowired
	private PersonaService personaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResonseDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// obtenemos el token
		String token = jwtTokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JwtAuthResonseDTO(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registrarUsuario(@Valid @RequestBody SignUpDTO signUpDTO) {


		Usuario usuario = new Usuario();

		// Búsqueda de la persona según la cédula

		Persona persona = personaService.getPersonaByCedula(signUpDTO.getCedula());

		
		usuario.setPersona(persona);
		
		if (usuarioService.existsByUsername(signUpDTO.getUsername())) {

			return new ResponseEntity<>("Nombre de usuario ingresado, ya se encuentra registrado",
					HttpStatus.BAD_REQUEST);

		}
		usuario.setUsername(signUpDTO.getUsername());
		usuario.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

		Rol roles = new Rol();

		if (signUpDTO.getRol().equals("usuario")) {

			roles = rolRepository.findByNombre("ROLE_USER").get();
		} else if (signUpDTO.getRol().equals("admin")) {
			roles = rolRepository.findByNombre("ROLE_ADMIN").get();
		} else {
			roles = rolRepository.findByNombre(signUpDTO.getRol()).get();
		}

		usuario.setRoles(Collections.singleton(roles));

		usuarioService.createUsuario(usuario);
		return new ResponseEntity<>("Usuario Registrado exitosamente", HttpStatus.CREATED);

	}

}
