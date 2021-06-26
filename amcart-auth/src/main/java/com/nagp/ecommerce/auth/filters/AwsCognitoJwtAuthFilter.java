package com.nagp.ecommerce.auth.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nagp.ecommerce.auth.jwt.AwsCognitoIdTokenProcessor;

@Component
public class AwsCognitoJwtAuthFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(AwsCognitoJwtAuthFilter.class);

	@Autowired
	private AwsCognitoIdTokenProcessor cognitoIdTokenProcessor;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Authentication authentication;
		try {
			authentication = this.cognitoIdTokenProcessor.authenticate((HttpServletRequest) request);

			if (authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception var6) {
			logger.error("Cognito ID Token processing error", var6);
			SecurityContextHolder.clearContext();
		}

		filterChain.doFilter(request, response);
	}
}
