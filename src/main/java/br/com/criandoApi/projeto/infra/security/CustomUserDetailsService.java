package br.com.criandoApi.projeto.infra.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.repository.UsuarioRepository;


@Component
public class CustomUserDetailsService  implements UserDetailsService{
	
	@Autowired
    private UsuarioRepository repository;
    
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Usuario user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        return new org.springframework.security.core.userdetails.User(
	            user.getEmail(), 
	            user.getSenha(), 
	            Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
	        );
	    }
	
}
