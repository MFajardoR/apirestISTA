package com.pms.service.implementation;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.entity.Evidencia;
import com.pms.exception.ResourceNotFoundException;
import com.pms.repository.EvidenciaRepository;
import com.pms.service.EvidenciaService;

@Service
public class EvidenciaServiceImpl implements EvidenciaService {

	@Autowired
	private EvidenciaRepository evidenciaRepository;

	@Override
	@Transactional
	public Evidencia createEvidencia(Evidencia evidencia) {
		return evidenciaRepository.save(evidencia);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evidencia> getAllEvidencias() {

		return evidenciaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Evidencia getEvidenciaById(long id) {

		return evidenciaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evidencia", "id", id));
	}

	@Override
	@Transactional
	public Evidencia updateEvidencia(long id, Evidencia evidencia) {

		Evidencia e = evidenciaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Evidencia", "id", id));

		e.setIdactividad(evidencia.getIdactividad());
		e.setIdusuario(evidencia.getIdusuario());
		e.setUrlRecurso(evidencia.getUrlRecurso());
		e.setDescripcion(evidencia.getDescripcion());
		e.setFechaActualizacion(new Date(System.currentTimeMillis()));

		return evidenciaRepository.save(e);
	}

	@Override
	@Transactional
	public void deleteEvidencia(long id) {

		Evidencia e = evidenciaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Evidencia", "id", id));
		evidenciaRepository.delete(e);
	}

}
