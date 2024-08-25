package br.com.criandoApi.projeto.service;

import java.util.List;
import java.util.Optional;

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
	
	
	public Optional<Usuario> getUserById(Integer id) {
		return usuarioRepository.findById(id);
	}
	
	
	public Usuario createUser(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	
	
	
	public Usuario updateUser(Usuario usuario, Integer id) {
		Optional<Usuario> usuarioFind = usuarioRepository.findById(id);
		if(!usuarioFind.isPresent()) {
			throw new RuntimeException("Usuario não encontrado");
		}
		
		Usuario usuarioMod = usuarioFind.get();
		
		usuarioMod.setNome(usuario.getNome());
		usuarioMod.setEmail(usuario.getEmail());
		usuarioMod.setSenha(usuario.getSenha());
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
