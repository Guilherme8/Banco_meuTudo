package br.com.meutudo.banco.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meutudo.banco.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByContaOrigemIdAndDataTransferenciaAfter(long id, LocalDateTime dataTransferencia);
    List<Transaction> findByContaOrigemIdAndExecutada(long id, boolean executada);
    Optional<Transaction> findByTransferenciaEstornadaId(long id);
}
