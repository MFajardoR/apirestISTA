package com.pms.service;

import java.util.List;

import com.pms.entity.Evidencia;

public interface EvidenciaService {
	
	public Evidencia createEvidencia(Evidencia evidencia);
	
	public List<Evidencia> getAllEvidencias();
	
	public Evidencia getEvidenciaById(long id);
	
	public Evidencia updateEvidencia(long id, Evidencia evidencia);
	
	public void deleteEvidencia(long id);

}
