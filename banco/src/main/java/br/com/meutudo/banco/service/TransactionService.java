package br.com.meutudo.banco.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.meutudo.banco.controller.dto.TransactionDto;
import br.com.meutudo.banco.controller.dto.TransactionFutureDto;
import br.com.meutudo.banco.controller.dto.TransactionFutureReturnDto;
import br.com.meutudo.banco.controller.dto.TransactionReturnDto;
import br.com.meutudo.banco.enums.TipoTransacaoEnum;
import br.com.meutudo.banco.exception.ContaDestinoNaoEncontradaException;
import br.com.meutudo.banco.exception.ContaOrigemNaoEncontradaException;
import br.com.meutudo.banco.exception.ExceptionCustom;
import br.com.meutudo.banco.model.Conta;
import br.com.meutudo.banco.model.Transaction;
import br.com.meutudo.banco.repository.TransactionRepository;

public class TransactionService {

	 @Autowired
     private TransactionRepository transactionRepository;
     @Autowired
     private MovementService movementService;
     @Autowired
     private ContaService contaService;

	    // Cadastra uma transferencia comum
	    @Transactional
	    public void criar(TransactionDto transactionDto) {
	        Conta contaOrigem = contaService.findById(transactionDto.getIdContaOrigem()).orElseThrow(ContaOrigemNaoEncontradaException::new);
	        Conta contaDestino = contaService.findById(transactionDto.getIdContaDestino()).orElseThrow(ContaDestinoNaoEncontradaException::new);
	        

	        validarTransferencia(transactionDto);

	        Transaction transaction = new Transaction();
	        transaction.setDataTransferencia(LocalDateTime.now());
	        transaction.setExecutada(true);
	        transaction.setEstorno(false);
	        transaction.setValor(transactionDto.getValor());
	        transaction.setContaOrigem(contaOrigem);
	        transaction.setContaDestino(contaDestino);

	        Transaction transactionSave = transactionRepository.save(transaction);

	        movementService.criarDespesa(transaction.getValor(), transaction.getContaOrigem(), TipoTransacaoEnum.TRANSFERENCIA, transactionSave);
	        movementService.criarReceita(transaction.getValor(), transaction.getContaDestino(), TipoTransacaoEnum.TRANSFERENCIA, transactionSave);
	    }

	    // Estorna transaferencia
	    @Transactional
	    public void estornar(long id) {
	        Optional<Transaction> transferenciaOptional = transactionRepository.findById(id);

	        if (transferenciaOptional.isEmpty())
	            throw new ExceptionCustom("Transferência não encontrada.");

	        Transaction transaction = transferenciaOptional.get();

	        validarEstornoTransferencia(transaction);

	        Transaction transferenciaEstorno = new Transaction();
	        transferenciaEstorno.setEstorno(true);
	        transferenciaEstorno.setValor(transaction.getValor());
	        transferenciaEstorno.setDataTransferencia(LocalDateTime.now());
	        transferenciaEstorno.setTransferenciaEstornada(transaction);
	        transferenciaEstorno.setContaOrigem(transaction.getContaDestino());
	        transferenciaEstorno.setContaDestino(transaction.getContaOrigem());
	        transferenciaEstorno.setExecutada(true);

	        Transaction transferenciaSalva = transactionRepository.save(transferenciaEstorno);

	        movementService.criarDespesa(transferenciaEstorno.getValor(), transferenciaEstorno.getContaOrigem(), TipoTransacaoEnum.ESTORNO, transferenciaSalva);
	        movementService.criarReceita(transferenciaEstorno.getValor(), transferenciaEstorno.getContaDestino(), TipoTransacaoEnum.ESTORNO, transferenciaSalva);
	    }

	    // Cria transferencia futuras dividindo as parcelas
	    @Transactional 
	    public List<TransactionFutureReturnDto> criarFutura(TransactionFutureDto transactionFutureDto) {

	        validarTransferenciaFutura(transactionFutureDto);
	        
	        Conta contaOrigem = contaService.findById(transactionFutureDto.getIdContaOrigem()).orElseThrow(() -> new ContaOrigemNaoEncontradaException());
	        Conta contaDestino = contaService.findById(transactionFutureDto.getIdContaDestino()).orElseThrow(() -> new ContaDestinoNaoEncontradaException());

	        BigDecimal valorTotal = BigDecimal.valueOf(transactionFutureDto.getValor());
	        BigDecimal valorParcelado = valorTotal.divide(BigDecimal.valueOf(transactionFutureDto.getQuantidadeParcelas()), 2, RoundingMode.DOWN);

	        if (valorParcelado.doubleValue() == 0.0)
	            throw new ExceptionCustom("Valor parcelado não pode ser igual a zero.");

	        BigDecimal totalParcelado = valorParcelado.multiply(BigDecimal.valueOf(transactionFutureDto.getQuantidadeParcelas()));

	        boolean deveAlterarValorPrimeiraParcela = valorTotal.compareTo(totalParcelado) == 1;

	        List<Transaction> listaTransferencias = new ArrayList<>();

	        for(int parcela = 0; parcela < transactionFutureDto.getQuantidadeParcelas(); parcela++) {
	            BigDecimal valor = valorParcelado;

	            if (parcela == 0 && deveAlterarValorPrimeiraParcela)
	                valor = valor.add(valorTotal.subtract(totalParcelado));

	            LocalDate dataTransferencia = transactionFutureDto.getDataTransferencia().plusMonths(parcela);
	            Transaction transaction = new Transaction();
	            transaction.setDataTransferencia(LocalDateTime.of(dataTransferencia, LocalTime.now()));
	            transaction.setExecutada(false);
	            transaction.setEstorno(false);
	            transaction.setValor(valor.doubleValue());
	            transaction.setContaOrigem(contaOrigem);
	            transaction.setContaDestino(contaDestino);
	            listaTransferencias.add(transactionRepository.save(transaction));
	        }

	        return listaTransferencias.stream().map(TransactionFutureReturnDto::valueOf)
	                .collect(Collectors.toList());
	    }

	    // Busca transferencias futuras da conta
	    public List<TransactionFutureReturnDto> getFuturasByConta(long idConta) {
	        List<Transaction> listaTransferencia = transactionRepository.findByContaOrigemIdAndDataTransferenciaAfter(idConta, LocalDateTime.now());
	        return listaTransferencia.stream().map(TransactionFutureReturnDto::valueOf)
	                .collect(Collectors.toList());
	    }

	    // Busca as transferencias de uma conta
	    public List<TransactionReturnDto> getByConta(long idConta) {
	        List<Transaction> listaTransferencia = transactionRepository.findByContaOrigemIdAndExecutada(idConta, true);
	        return listaTransferencia.stream().map(TransactionReturnDto::valueOf)
	                .collect(Collectors.toList());
	    }

	    // Valida transferencia
	    private void validarTransferencia(TransactionDto transactionDto) {
	        if (transactionDto.getIdContaOrigem() == transactionDto.getIdContaDestino())
	            throw new ExceptionCustom("Conta origem igual conta destino.");
	        if (transactionDto.getValor() > contaService.getSaldoById(transactionDto.getIdContaOrigem()))
	            throw new ExceptionCustom("Saldo insuficiente.");
	    }

	    // Valida estorno
	    private void validarEstornoTransferencia(Transaction transferenciaEstornada) {

	        if (!transferenciaEstornada.isExecutada())
	            throw new ExceptionCustom("Transferência não realizada.");

	        Optional<Transaction> transferenciaOptional = transactionRepository.findByTransferenciaEstornadaId(transferenciaEstornada.getId());

	        if (transferenciaOptional.isPresent())
	            throw new ExceptionCustom("Transferência já estornada.");

	        validarTransferencia(new TransactionDto(transferenciaEstornada.getValor(), transferenciaEstornada.getContaDestino().getId(), transferenciaEstornada.getContaOrigem().getId() ));
	    }

	    // Valida transferencia futura
	    private void validarTransferenciaFutura(TransactionFutureDto transactionFutureDto) {
	        if (transactionFutureDto.getIdContaOrigem() == transactionFutureDto.getIdContaDestino())
	            throw new ExceptionCustom("Conta origem igual conta destino.");
	    }
}
