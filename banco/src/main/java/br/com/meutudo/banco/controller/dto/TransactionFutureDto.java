package br.com.meutudo.banco.controller.dto;

import java.time.LocalDate;

public class TransactionFutureDto extends TransactionDto {

    public TransactionFutureDto(double valor2, Long id, Long id2) {
		super(valor2, id, id2);
		// TODO Auto-generated constructor stub
	}
	private LocalDate dataTransferencia;
    private int quantidadeParcelas;
    
	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}
	public void setDataTransferencia(LocalDate dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
    
    
}
