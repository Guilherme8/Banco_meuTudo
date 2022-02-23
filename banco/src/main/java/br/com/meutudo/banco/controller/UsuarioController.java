package br.com.meutudo.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.meutudo.banco.repository.UsuarioRepository;
import br.com.meutudo.banco.controller.dto.UserDto;
import br.com.meutudo.banco.model.Usuario;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public List<UserDto> lista() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		 return UserDto.converter(usuarios);
	}
} 
