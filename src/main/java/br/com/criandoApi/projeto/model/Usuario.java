package br.com.criandoApi.projeto.model;


import br.com.criandoApi.projeto.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "usuario")
@Builder
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome é obrigatorio")
	@NotNull
	@NotEmpty
	@Column(name = "nome_completo", length = 200, nullable = false)
	@Size(min = 2, message = "O Nome deve ter no minimo 3 caracteres!")
	private String nome;
	
	
	@Column(name = "username", nullable = false)
	private String username;
	
	
	@Email(message = "Insira um email valido!")
	@NotBlank(message = "O email é obrigatorio")
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	
	@NotBlank(message = "A senha é obrigatoria")
	@Column(name = "senha", columnDefinition = "TEXT", nullable = false)
	private String senha;
	
	
	@NotBlank(message = "O telefone é obrigatorio")
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	
	

	@Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;
	
	

	
	
}
