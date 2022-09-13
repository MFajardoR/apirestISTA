package com.pms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.entity.Evidencia;
import com.pms.service.EvidenciaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/evidencias")
public class EvidenciaController {

	@Autowired
	private EvidenciaService evidenciaService;

	@GetMapping("/")
	public ResponseEntity<List<Evidencia>> listEvidencias() {

		return ResponseEntity.ok(evidenciaService.getAllEvidencias());
	}

	@GetMapping("/evidencia/{id}")
	public ResponseEntity<Evidencia> getEvidenciaById(@PathVariable long id) {
		return ResponseEntity.ok(evidenciaService.getEvidenciaById(id));
	}

	@PostMapping("/evidencia")
	public ResponseEntity<Evidencia> saveEvidencia(@Valid @RequestBody Evidencia evidencia) {
		return new ResponseEntity<>(evidenciaService.createEvidencia(evidencia), HttpStatus.CREATED);
	}

	@PutMapping("/evidencia/{id}")
	public ResponseEntity<Evidencia> updateEvidencia(@PathVariable long id, @Valid @RequestBody Evidencia evidencia) {

		Evidencia e = evidenciaService.updateEvidencia(id, evidencia);
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}

	@DeleteMapping("/evidencia/{id}")
	public ResponseEntity<String> deleteEvidencia(@PathVariable long id) {
		evidenciaService.deleteEvidencia(id);
		return new ResponseEntity<>("Evidencia eliminada con id: " + id, HttpStatus.OK);
	}

}
