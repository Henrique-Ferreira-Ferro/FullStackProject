package br.com.criandoApi.projeto.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.criandoApi.projeto.model.Usuario;

@Service
public class TokenService {
	
	 @Value("${api.security.token.secret}")
	    private String secret;
	    public String generateToken(Usuario user){
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            
	           String role = user.getRole().getRole().toUpperCase();
	            		
	            
	            String token = JWT.create()
	                    .withIssuer("login-auth-api")
	                    .withSubject(user.getEmail())
	                    .withClaim("roles", Collections.singletonList(role))
	                    .withExpiresAt(this.generateExpirationDate())
	                    .sign(algorithm);
	            return token;
	        } catch (JWTCreationException exception){
	            throw new RuntimeException("Error while authenticating");
	        }
	    }

	    public String validateToken(String token){
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            return JWT.require(algorithm)
	                    .withIssuer("login-auth-api")
	                    .build()
	                    .verify(token)
	                    .getSubject();
	        } catch (JWTVerificationException exception) {
	            return null;
	        }
	    }

	    private Instant generateExpirationDate(){
	        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	    }
	
	
}
