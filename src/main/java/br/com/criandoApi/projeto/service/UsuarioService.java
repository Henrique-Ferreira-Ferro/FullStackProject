package br.com.criandoApi.projeto.service;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.criandoApi.projeto.dto.CreateUserDTO;
import br.com.criandoApi.projeto.dto.JwtTokenDTO;
import br.com.criandoApi.projeto.dto.LoginUserDTO;
import br.com.criandoApi.projeto.model.ModelRole;
import br.com.criandoApi.projeto.model.ModelUserDetailsImpl;
import br.com.criandoApi.projeto.model.Usuario;
import br.com.criandoApi.projeto.repository.UsuarioRepository;
import br.com.criandoApi.projeto.security.SecurityConfig;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	 @Autowired
	    private SecurityConfig securityConfig;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtTokenService jwtTokenService;
	
	


	public List<Usuario> getAllUsers(){
		return usuarioRepository.findAll();
	}
	
	
	public Optional<Usuario> getUserById(Integer id) {
		return usuarioRepository.findById(id);
	}
	
	
	public void salvarUsuario(CreateUserDTO createUserDto) {
        Usuario newUser = Usuario.builder()
                .email(createUserDto.email())
                .senha(securityConfig.passwordEncoder().encode(createUserDto.password()))
                .roles(List.of(ModelRole.builder().name(createUserDto.role()).build()))
                .build();

        usuarioRepository.save(newUser);
    }
	
	
	
	
	public Usuario updateUser(Usuario usuario, Integer id) {
		Optional<Usuario> usuarioFind = usuarioRepository.findById(id);
		if(!usuarioFind.isPresent()) {
			throw new RuntimeException("Usuario não encontrado");
		}
		
		Usuario usuarioMod = usuarioFind.get();
		
		usuarioMod.setNome(usuario.getNome());
		usuarioMod.setEmail(usuario.getEmail());
		usuarioMod.setTelefone(usuario.getTelefone());
		usuarioMod.setUsername(usuario.getUsername());
		usuarioRepository.save(usuarioMod);
		return usuarioMod;
	}
	
	
	public String deleteUser(Integer id) {
		usuarioRepository.deleteById(id);
		return "Usuario deletado com sucesso!";
	}

	 public JwtTokenDTO autenticarUsuario(LoginUserDTO loginUserDto) {
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

	        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	        ModelUserDetailsImpl modelUserDetails = (ModelUserDetailsImpl) authentication.getPrincipal();
	        return new JwtTokenDTO(jwtTokenService.generateToken(modelUserDetails));
	    }
	
	
	
	
	
}
