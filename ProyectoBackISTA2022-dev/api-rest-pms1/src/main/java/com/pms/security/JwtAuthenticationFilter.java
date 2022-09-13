package com.pms.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

// Filtro que validará el token para realizar peticiones

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// obtenemos el token de la solicitud HTTP
		String token = getJWTOfRequest(request);

		if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			// Obtenemos el username del token
			String username = jwtTokenProvider.getUsernameOfJWT(token);

			// cargamos el usuario asociado al token
			UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			// establecemos la seguridad
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		}

		filterChain.doFilter(request, response);

	}

	// Bearer token de acceso
	private String getJWTOfRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {

			return bearerToken.substring(7, bearerToken.length());

		}

		return null;
	}

}
