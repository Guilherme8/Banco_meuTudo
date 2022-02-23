package br.com.meutudo.banco.controller.dto;

public class TransactionDto {

    private double valor;
    private long idContaOrigem;
    private long idContaDestino;
	public TransactionDto(double valor2, Long id, Long id2) {
		// TODO Auto-generated constructor stub
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public long getIdContaOrigem() {
		return idContaOrigem;
	}
	public void setIdContaOrigem(long idContaOrigem) {
		this.idContaOrigem = idContaOrigem;
	}
	public long getIdContaDestino() {
		return idContaDestino;
	}
	public void setIdContaDestino(long idContaDestino) {
		this.idContaDestino = idContaDestino;
	}
    
    
}
