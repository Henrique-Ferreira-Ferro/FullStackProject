package br.com.criandoApi.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
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
	
	
	
	
	
	
	
}
