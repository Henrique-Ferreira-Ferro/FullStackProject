package br.com.criandoApi.projeto.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	
	private PasswordEncoder passwordEncoder;
	
	
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}


	public List<Usuario> getAllUsers(){
		return usuarioRepository.findAll();
	}
	
	
	public Optional<Usuario> getUserById(Integer id) {
		return usuarioRepository.findById(id);
	}
	
	
	public Usuario createUser(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setRole(usuario.getRole());
		usuario.setSenha(encoder);
		return usuarioRepository.save(usuario);
	}
	
	
	
	
	public Usuario updateUser(Usuario usuario, Integer id) {
		Optional<Usuario> usuarioFind = usuarioRepository.findById(id);
		if(!usuarioFind.isPresent()) {
			throw new ObjectNotFoundException(usuario.getId(), Usuario.class.getName());
		}
		
		Usuario usuarioMod = usuarioFind.get();
		
		usuarioMod.setNome(usuario.getNome());
		usuarioMod.setEmail(usuario.getEmail());
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		usuarioMod.setTelefone(usuario.getTelefone());
		usuarioMod.setUsername(usuario.getUsername());
		usuarioRepository.save(usuarioMod);
		return usuarioMod;
	}
	
	
	public String deleteUser(Integer id) {
		usuarioRepository.deleteById(id);
		return "Usuario deletado com sucesso!";
	}


	
	
	
	
	
}
