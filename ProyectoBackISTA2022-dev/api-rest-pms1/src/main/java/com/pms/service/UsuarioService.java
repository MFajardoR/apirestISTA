package com.pms.service;

import java.util.List;

import com.pms.entity.Usuario;

public interface UsuarioService {
	
	public Usuario createUsuario(Usuario usuario);
	
	public List<Usuario> getAllUsers();
	
	public Usuario getUsuarioById(long id);
	
	public Usuario getUsuarioByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
	public Usuario updateUsuario(long id, Usuario usuario);
	
	public void deleteUsuario(long id);

}
