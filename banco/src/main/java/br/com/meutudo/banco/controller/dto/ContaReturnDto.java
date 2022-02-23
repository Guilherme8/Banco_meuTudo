package br.com.meutudo.banco.controller.dto;

import br.com.meutudo.banco.model.Conta;

public class ContaReturnDto {

	 private long id;
	    private String banco;
	    private String agencia;
	    private String numero;
	    private String digito;
	    
		public ContaReturnDto(Long id2, String agencia2, String numero2, String digito2) {
		}

		public static ContaReturnDto valueOf(Conta conta) {
	        return new ContaReturnDto(conta.getId(), conta.getAgencia(), conta.getNumero(), conta.getDigito());
	    }

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getBanco() {
			return banco;
		}

		public void setBanco(String banco) {
			this.banco = banco;
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
		
		
}
