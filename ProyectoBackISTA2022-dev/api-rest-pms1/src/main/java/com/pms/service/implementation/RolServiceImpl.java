package com.pms.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.entity.Rol;
import com.pms.exception.ResourceNotFoundException;
import com.pms.repository.RolRepository;
import com.pms.service.RolService;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	@Transactional
	public Rol createRol(Rol rol) {
		return rolRepository.save(rol);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Rol> getAllRoles() {
		return rolRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Rol getRolById(long id) {
		return rolRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Rol", "id", id));
	}

	@Override
	@Transactional
	public Rol updateRol(long id, Rol rol) {
		Rol r = rolRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Rol", "id", id));
		
		r.setNombre(rol.getNombre());
		r.setDescripcion(rol.getDescripcion());
		
		return rolRepository.save(r);
	}

	@Override
	@Transactional
	public void deleteRol(long id) {
		
		Rol r = rolRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Rol", "id", id));
		rolRepository.delete(r);
	}

}
