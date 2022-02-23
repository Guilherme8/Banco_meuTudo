package br.com.meutudo.banco.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Table(name = "MOVIMENTACAO")
public class Movement {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID", unique=true, updatable = false, nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "DATA_CRIACAO", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "TIPO", updatable = false, nullable = false)
    private int tipo;

    @Column(name = "TIPO_TRANSACAO", updatable = false, nullable = false)
    private int tipoTransacao;

    @Column(name = "VALOR", updatable = false, nullable = false)
    private double valor;

    @ManyToOne
    @JoinColumn(name = "CONTA_FK", updatable = false, nullable = false)
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "TRANSFERENCIA_FK", updatable = false)
    private Transaction transaction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(int tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
    
    
}
