package com.pms.service;

import java.util.List;

import com.pms.entity.Rol;

public interface RolService {
	
	public Rol createRol(Rol rol);
	
	public List<Rol> getAllRoles();
	
	public Rol getRolById(long id);
	
	public Rol updateRol(long id, Rol rol);
	
	public void deleteRol(long id);
}
