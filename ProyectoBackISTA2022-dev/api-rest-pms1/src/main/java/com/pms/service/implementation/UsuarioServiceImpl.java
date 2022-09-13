package com.pms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pms.entity.Usuario;
import com.pms.exception.ResourceNotFoundException;
import com.pms.repository.UsuarioRepository;
import com.pms.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Usuario createUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> getAllUsers() {
		
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario getUsuarioById(long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
	}

	@Override
	public Usuario getUsuarioByUsername(String username) {
		
		return usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "username", username));
	}

	@Override
	public Usuario updateUsuario(long id, Usuario usuario) {
		
		Usuario u = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
		
		u.setUsername(usuario.getUsername());
		u.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		return usuarioRepository.save(u);
	}

	@Override
	public void deleteUsuario(long id) {
		
		Usuario u = usuarioRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
		
		usuarioRepository.delete(u);
		
	}

	@Override
	public Boolean existsByUsername(String username) {
		return usuarioRepository.existsByUsername(username);
	}

}
