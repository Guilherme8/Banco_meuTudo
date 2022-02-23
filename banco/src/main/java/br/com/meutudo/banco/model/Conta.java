package br.com.meutudo.banco.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CONTA")
public class Conta {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", unique=true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "AGENCIA")
    private String agencia;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "DIGITO")
    private String digito;

    @OneToMany(mappedBy = "conta", targetEntity = Movement.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movement> listaMovimentacao = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDigito() {
		return digito;
	}

	public void setDigito(String digito) {
		this.digito = digito;
	}

	public List<Movement> getListaMovimentacao() {
		return listaMovimentacao;
	}

	public void setListaMovimentacao(List<Movement> listaMovimentacao) {
		this.listaMovimentacao = listaMovimentacao;
	}
	
	
}
