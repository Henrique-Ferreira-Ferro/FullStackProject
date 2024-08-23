package br.com.criandoApi.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public List<Usuario> getAllUsers(){
		return usuarioRepository.findAll();
	}
	
	
	public Usuario createUser(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
}
