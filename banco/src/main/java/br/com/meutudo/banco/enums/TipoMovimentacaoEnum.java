package br.com.meutudo.banco.enums;

public enum TipoMovimentacaoEnum {
	
	RECEITA(1, "RECEITA"),
    DESPESA(2, "DESPESA");

    private Integer codigo;
    private String descricao;

    TipoMovimentacaoEnum(Integer codigo, String descricao) {
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
