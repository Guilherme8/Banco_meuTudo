package br.com.meutudo.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.meutudo.banco.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
	
	@Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.conta.id = :idConta")
    double getSomatorioByConta(@Param("idConta") long idConta);
}
