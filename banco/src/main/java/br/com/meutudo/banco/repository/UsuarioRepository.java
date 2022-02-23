package br.com.meutudo.banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meutudo.banco.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional <Usuario> findByEmail(String email);

}
