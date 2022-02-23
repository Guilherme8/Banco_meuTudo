package br.com.meutudo.banco.enums;

public enum TipoTransacaoEnum {

	SAQUE(1, "SAQUE"),
    DEPOSITO(2, "DEPÓSITO"),
    TRANSFERENCIA(3, "TRANSFERÊNCIA"),
    INVESTIMENTO(4, "INVESTIMENTO"),
    SALARIO(5, "SALÁRIO"),
    ESTORNO(6, "ESTORNO");

    private Integer codigo;
    private String descricao;

    TipoTransacaoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
    
}
