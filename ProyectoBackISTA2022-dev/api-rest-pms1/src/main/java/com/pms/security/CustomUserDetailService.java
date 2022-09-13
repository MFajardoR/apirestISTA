package com.pms.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pms.entity.Rol;
import com.pms.entity.Usuario;
import com.pms.repository.UsuarioRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("No se ha encontrado el nombre de usuario: " + username));

		return new User(usuario.getUsername(), usuario.getPassword(), mapRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRoles(Set<Rol> roles) {

		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());

	}

}
