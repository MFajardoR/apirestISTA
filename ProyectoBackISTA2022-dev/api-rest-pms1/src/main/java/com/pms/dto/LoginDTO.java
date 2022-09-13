package com.pms.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	
	@NotBlank(message = "Ingrese su nombre de usuario")
	private String username;
	
	@NotBlank(message = "Ingrese su contraseña")
	private String password;
	
	

}
