package br.com.criandoApi.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsers() {
		return ResponseEntity.status(200).body(usuarioService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> getById(@PathVariable Integer id){
		return ResponseEntity.status(200).body(usuarioService.getUserById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Usuario> createUser(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(201).body( usuarioService.createUser(usuario));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Usuario> updateUser(@Valid @RequestBody Usuario usuario, @PathVariable Integer id) {
		return ResponseEntity.status(200).body(usuarioService.updateUser(usuario, id));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		return ResponseEntity.status(204).body(usuarioService.deleteUser(id));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> validarSenha(@RequestBody Usuario usuario){
		
		Boolean valid = usuarioService.validarSenha(usuario);
		if(!valid) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.status(200).build();
	}
	
	
	
	
}
