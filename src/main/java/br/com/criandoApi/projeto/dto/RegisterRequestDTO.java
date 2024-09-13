package br.com.criandoApi.projeto.dto;

import br.com.criandoApi.projeto.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
	
	private String nome;
	private String username;
	private String email;
	private String senha;
	private String telefone;
	private UserRole role;
	
}
