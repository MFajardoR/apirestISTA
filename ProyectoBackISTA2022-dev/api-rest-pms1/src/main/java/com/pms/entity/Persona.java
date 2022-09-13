package com.pms.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona", uniqueConstraints = { @UniqueConstraint(columnNames = { "cedula" }) })
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idpersona;

	@Column(nullable = false, length = 10)
	@NotBlank
	@Size(min = 10, max = 10, message = "La cédula debe tener 10 caracteres")
	private String cedula;

	@Column(nullable = false, length = 30)
	@NotBlank
	@Size(min = 2, message = "El nombre debería tener al menos 2 caracteres")
	private String nombre;

	@Column(nullable = false, length = 30)
	@NotBlank
	@Size(min = 2, message = "El apellido debería tener al menos 2 caracteres")
	private String apellido;

	@Email
	@Column(nullable = false, length = 255)
	private String email;

	@Column(nullable = false, length = 10)
	@NotBlank
	@Size(min = 10, max = 10, message = "El teléfono debe tener 10 caracteres")
	private String telefono;

	private Date fecha_nacimiento;

}
