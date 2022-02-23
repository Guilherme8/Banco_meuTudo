package br.com.meutudo.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meutudo.banco.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	 Conta findById(int conta);
	 
}
