package br.com.criandoApi.projeto.security;

import java.io.IOException;
import java.util.Arrays;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.criandoApi.projeto.model.ModelUserDetailsImpl;
import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (verificaEndpointsPublicos(request)) {
            String token = recuperaToken(request);
            if (token != null) {
                String subject = jwtTokenService.getSubjectFromToken(token);
                Usuario modelUser = userRepository.findByEmail(subject).get();
                ModelUserDetailsImpl modelUserDetails = new ModelUserDetailsImpl(modelUser);
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                modelUserDetails.getUsername(),
                                null,
                                modelUserDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("Token inexistente!");
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean verificaEndpointsPublicos(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList("/api/users/login", "/api/users").contains(requestURI);
    }

    private String recuperaToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

	
}
