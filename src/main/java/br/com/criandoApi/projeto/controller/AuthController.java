package br.com.criandoApi.projeto.controller;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.criandoApi.projeto.dto.LoginRequestDTO;
import br.com.criandoApi.projeto.dto.RegisterRequestDTO;
import br.com.criandoApi.projeto.dto.ResponseDTO;
import br.com.criandoApi.projeto.enums.UserRole;
import br.com.criandoApi.projeto.exceptions.BadRequestException;
import br.com.criandoApi.projeto.infra.security.TokenService;
import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

	private final UsuarioRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	
	@PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Usuario user = this.repository.findByEmail(body.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
        if(passwordEncoder.matches(body.getSenha(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token));
        }
        	throw new ObjectNotFoundException(user.getId(), Usuario.class.getName());
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<Usuario> user = this.repository.findByEmail(body.getEmail());

        if(user.isEmpty()) {
        	Usuario newUser = new Usuario();
            newUser.setNome(body.getNome());
            newUser.setUsername(body.getUsername());
            newUser.setEmail(body.getEmail());
            newUser.setSenha(passwordEncoder.encode(body.getSenha()));
            newUser.setTelefone(body.getTelefone());
            newUser.setRole(UserRole.USER);
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));
        }
        throw new BadRequestException("Não foi possivel registrar o usuario!!");
    }
	
	
}
