package com.nagp.ecommerce.auth.configuration;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

@Configuration
public class AwsJwtConfiguration {

	@Autowired
	private JwtConfiguration jwtConfiguration;

	@Bean
	public ConfigurableJWTProcessor configurableJWTProcessor() throws MalformedURLException {
		ResourceRetriever resourceRetriever = new DefaultResourceRetriever(jwtConfiguration.getConnectionTimeout(),
				jwtConfiguration.getReadTimeout());
		URL jwkURL = new URL(jwtConfiguration.getJwkUrl());
		JWKSource keySource = new RemoteJWKSet(jwkURL, resourceRetriever);
		ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
		JWSKeySelector keySelector = new JWSVerificationKeySelector(RS256, keySource);
		jwtProcessor.setJWSKeySelector(keySelector);
		return jwtProcessor;
	}
}
