package br.com.criandoApi.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> getAllUsers() {
		return usuarioService.getAllUsers();
	}
	
	@PostMapping("/create")
	public Usuario createUser(@RequestBody Usuario usuario) {
		return usuarioService.createUser(usuario);
	}
	
	
}
