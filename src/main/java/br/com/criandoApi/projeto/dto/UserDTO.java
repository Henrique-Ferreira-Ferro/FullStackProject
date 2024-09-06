package br.com.criandoApi.projeto.dto;

import java.util.List;

import br.com.criandoApi.projeto.enums.Role;

public record UserDTO(Long id, String email, List<Role> roles) {

}
