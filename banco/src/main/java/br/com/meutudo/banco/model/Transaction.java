package br.com.meutudo.banco.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Transaction {

	 @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID", unique=true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "DATA_TRANSFERENCIA", updatable = false, nullable = false)
    private LocalDateTime dataTransferencia;

    @CreationTimestamp
    @Column(name = "DATA_CRIACAO", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "VALOR", updatable = false, nullable = false)
    private double valor;

    @Column(name = "EXECUTADA", updatable = false)
    private boolean executada;

    @Column(name = "ESTORNO", updatable = false)
    private boolean estorno;

    @ManyToOne
    @JoinColumn(name="CONTA_ORIGEM_FK", updatable = false, nullable = false)
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name="CONTA_DESTINO_FK", updatable = false, nullable = false)
    private Conta contaDestino;

    @OneToOne
    @JoinColumn(name = "TRANSFERENCIA_ESTORNADA_FK", updatable = false)
    private Transaction transferenciaEstornada;
	
	public Transaction() {
		
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

	public boolean isExecutada() {
		return executada;
	}

	public void setExecutada(boolean executada) {
		this.executada = executada;
	}

	public boolean isEstorno() {
		return estorno;
	}

	public void setEstorno(boolean estorno) {
		this.estorno = estorno;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Transaction getTransferenciaEstornada() {
		return transferenciaEstornada;
	}

	public void setTransferenciaEstornada(Transaction transferenciaEstornada) {
		this.transferenciaEstornada = transferenciaEstornada;
	}

//	public Transaction(LocalDateTime dt_scheduling, float balance) {
//		super();
//		this.dt_scheduling = dt_scheduling;
//		this.balance = balance;
//	}
	
	
	
}
