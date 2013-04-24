package br.feevale.droidhospital.db;

import java.io.Serializable;

public class Interpretador implements Serializable {
	
	private static final long serialVersionUID = -6029962071947699788L;

	public final static int VALIDA_LOGIN = 1;
	public final static int LISTA_LEITOS = 2;
	public final static int LISTA_PESSOAS = 3;
	public final static int LISTA_MEDICAMENTOS = 4;
	
	private Integer cdTransacao;
	
	public void setCdTransacao(Integer cdTransacao) {
		this.cdTransacao = cdTransacao;
	}
	
	public Integer getCdTransacao() {
		return cdTransacao;
	}
}