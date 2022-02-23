package br.com.meutudo.banco.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.meutudo.banco.model.Usuario;

public class UserDto {
	
	private Long id;
	private String nome;
	private String email;
	
	public UserDto(Usuario usuario) { 
	    this.id = usuario.getId(); 
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	
	public static List<UserDto> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(UserDto::new).collect(Collectors.toList());
	}
}
