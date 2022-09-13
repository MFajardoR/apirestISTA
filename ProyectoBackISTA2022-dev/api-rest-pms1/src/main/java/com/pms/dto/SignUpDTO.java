package com.pms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
	
	@NotBlank(message = "Número de cédula no ingresado")
	@Size(min = 10, max = 10, message = "La cédula debe tener 10 caracteres")
	private String cedula;
	
	@NotBlank(message = "Nombre de usuario no ingresado")
	@Size(min = 4, max = 45, message = "El nombre de usuario debe tener entre 4 a 45 caracteres")
	private String username;
	@NotBlank
	@Size(min = 4, message = "La contraseña debe tener como mínimo 4 caracteres")
	private String password;
	
	@NotBlank(message = "Ingrese el rol de la cuenta")
	private String rol;
	

}
