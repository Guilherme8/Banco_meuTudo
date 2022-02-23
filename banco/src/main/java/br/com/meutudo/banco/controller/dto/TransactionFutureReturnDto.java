package br.com.meutudo.banco.controller.dto;

import java.time.LocalDateTime;

import br.com.meutudo.banco.model.Transaction;

public class TransactionFutureReturnDto {

	private Long id;
    private LocalDateTime dataTransferencia;
    private LocalDateTime dataCriacao;
    private double valor;
    private ContaReturnDto contaDestino;

    public TransactionFutureReturnDto(Long id2, LocalDateTime dataTransferencia2, LocalDateTime dataCriacao2,
			double valor2, ContaReturnDto valueOf) {
		// TODO Auto-generated constructor stub
	}

	public static TransactionFutureReturnDto valueOf(Transaction transaction) {
        return new TransactionFutureReturnDto(transaction.getId(), transaction.getDataTransferencia(),
        		transaction.getDataCriacao(), transaction.getValor(), ContaReturnDto.valueOf(transaction.getContaDestino()));
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDateTime dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public ContaReturnDto getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(ContaReturnDto contaDestino) {
		this.contaDestino = contaDestino;
	}
	
	
}
