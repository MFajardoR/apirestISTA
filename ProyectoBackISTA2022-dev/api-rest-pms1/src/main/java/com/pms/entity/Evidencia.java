package com.pms.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "evidencia")
public class Evidencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idevidencia;

	private long idactividad;

	private long idusuario;

	@Column(nullable = false, length = 255)
	@NotBlank(message = "Ingrese la url del documento de evidencia")
	private String urlRecurso;
	
	@Column(nullable = false,length = 45)
	@NotBlank(message = "Describa la evidencia ingresada")
	private String descripcion;

	private Date fechaCreacion;

	private Date fechaActualizacion;

	@PrePersist
	public void prePersist() {
		// para adicionar la fecha de creacion
		fechaCreacion = new Date(System.currentTimeMillis());
	}

}
