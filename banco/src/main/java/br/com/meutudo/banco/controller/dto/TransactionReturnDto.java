package br.com.meutudo.banco.controller.dto;

import java.time.LocalDateTime;

import br.com.meutudo.banco.model.Transaction;

public class TransactionReturnDto {

	private Long id;
    private LocalDateTime dataTransferencia;
    private double valor;
    private ContaReturnDto contaDestino;

    public TransactionReturnDto(Long id2, LocalDateTime dataTransferencia2, double valor2, ContaReturnDto valueOf) {
		// TODO Auto-generated constructor stub
	}

	public static TransactionReturnDto valueOf(Transaction transaction) {
        return new TransactionReturnDto(transaction.getId(), transaction.getDataTransferencia(),
        		transaction.getValor(), ContaReturnDto.valueOf(transaction.getContaDestino()));
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
