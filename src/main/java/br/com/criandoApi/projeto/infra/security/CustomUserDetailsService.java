package br.com.criandoApi.projeto.infra.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.repository.UsuarioRepository;


@Component
public class CustomUserDetailsService {
	
	@Autowired
    private UsuarioRepository repository;
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(), new ArrayList<>());
    }
	
}
