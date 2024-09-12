package br.com.criandoApi.projeto.dto;

import br.com.criandoApi.projeto.enums.Role;

public record CreateUserDTO(String email, String password, Role role) {

}
