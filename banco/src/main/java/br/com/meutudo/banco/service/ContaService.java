package br.com.meutudo.banco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.meutudo.banco.controller.dto.TransactionFutureReturnDto;
import br.com.meutudo.banco.controller.dto.TransactionReturnDto;
import br.com.meutudo.banco.exception.ExceptionCustom;
import br.com.meutudo.banco.model.Conta;
import br.com.meutudo.banco.repository.ContaRepository;

public class ContaService {

	 @Autowired
	 private ContaRepository contaRepository;
	 @Autowired
	 private MovementService movementService;
	 @Autowired
	 private TransactionService transactionService;

	    // Show conta ID
	    public Optional<Conta> findById(long id) {
	        return contaRepository.findById(id);
	    }

	    // Consulta saldo
	    public Double getSaldoById(long id) {
	        verificaContaExiste(id);

	        return movementService.getSomatorioByConta(id);
	    }

	    // Consulta transferencias futuras
	    public List<TransactionFutureReturnDto> getTransferenciasFuturasById(long id) {
	        verificaContaExiste(id);

	        return transactionService.getFuturasByConta(id);
	    }

	    // Consulta transferencias
	    public List<TransactionReturnDto> getTransferenciasById(long id) {
	        verificaContaExiste(id);

	        return transactionService.getByConta(id);
	    }

	    // Verifica se conta existe
	    private void verificaContaExiste(long id) {
	        Optional<Conta> contaOptional = contaRepository.findById(id);

	        if (contaOptional.isEmpty())
	        	throw new ExceptionCustom("Conta não encontrada!");
	    }
}
