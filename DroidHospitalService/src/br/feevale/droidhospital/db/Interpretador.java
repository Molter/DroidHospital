package br.feevale.droidhospital.db;

import java.io.Serializable;

public class Interpretador implements Serializable {

	private static final long serialVersionUID = -6029962071947699788L;

	public final static int VALIDA_LOGIN = 1;
	public final static int LISTA_LEITOS = 2;
	public final static int LISTA_PESSOAS = 3;
	public final static int LISTA_MEDICAMENTOS = 4;
	public final static int LISTA_PACIENTES = 5;
	public final static int DADOS_PACIENTE = 6;
<<<<<<< HEAD
	public final static int BUSCA_MEDICAMENTO = 7;
	public final static int CREATE_PRESCRICAO = 8;
	public final static int LISTA_APLICACOES = 9;
	public final static int ENVIA_APLICACAO = 10;
	public final static int AGENDA_APLICACOES = 11;
<<<<<<< HEAD

=======
	
=======
>>>>>>> Web Services Sync Tasks
	
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
	private Integer cdTransacao;

	public void setCdTransacao(Integer cdTransacao) {
		this.cdTransacao = cdTransacao;
	}

	public Integer getCdTransacao() {
		return cdTransacao;
	}
}