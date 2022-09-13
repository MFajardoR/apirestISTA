package com.pms.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor()
@Getter
@Setter
public class JwtAuthResonseDTO {

	private String accessToken;
	private String tokenType = "Bearer";
	
	
	public JwtAuthResonseDTO(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	
	
	
	

}
