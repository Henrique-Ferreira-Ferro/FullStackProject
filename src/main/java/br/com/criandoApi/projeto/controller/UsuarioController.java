package br.com.criandoApi.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsers() {
		List<Usuario> users = usuarioService.getAllUsers();
		return ResponseEntity.status(200).body(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> getById(@PathVariable Integer id){
		
		Optional<Usuario> user= usuarioService.getUserById(id);
		return ResponseEntity.status(200).body(user);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
		Usuario user = usuarioService.createUser(usuario);
		return ResponseEntity.status(201).body(user);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Usuario> updateUser(@RequestBody Usuario usuario, @PathVariable Integer id) {
		
		Usuario user = usuarioService.updateUser(usuario, id);
		return ResponseEntity.status(201).body(user);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		usuarioService.deleteUser(id);
		return ResponseEntity.status(204).build();
	}
	
	
}
