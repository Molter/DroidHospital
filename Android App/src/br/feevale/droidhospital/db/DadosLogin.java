package br.feevale.droidhospital.db;

public class DadosLogin extends Interpretador {
	
	private static final long serialVersionUID = -4645150442535663923L;
	
	private String[] dados;
	
	public String[] getDados() {
		return dados;
	}

	public DadosLogin(String[] dados) {
		this.dados = dados;
	}
}
