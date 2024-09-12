package br.com.criandoApi.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.criandoApi.projeto.model.ModelUserDetailsImpl;
import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.repository.UsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	   @Autowired
	    private UsuarioRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Usuario modelUser = userRepository.findByEmail(username)
	                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
	        return new ModelUserDetailsImpl(modelUser);
	    }
}
