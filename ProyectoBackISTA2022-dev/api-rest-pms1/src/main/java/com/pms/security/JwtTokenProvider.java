package com.pms.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pms.exception.AppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {

		String username = authentication.getName();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);

		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();

		return token;
	}

	public String getUsernameOfJWT(String token) {

		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			throw new AppException(HttpStatus.BAD_REQUEST, "Firma JWT no válida");
		} catch (MalformedJwtException e) {
			throw new AppException(HttpStatus.BAD_REQUEST, "Token JWT no válida");
		} catch (ExpiredJwtException e) {
			throw new AppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
		} catch (UnsupportedJwtException e) {
			throw new AppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
		} catch (IllegalArgumentException e) {
			throw new AppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT está vacía");
		}
	}

}
