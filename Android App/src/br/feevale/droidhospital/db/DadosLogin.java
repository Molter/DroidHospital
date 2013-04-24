package br.feevale.droidhospital.db;


public class DadosLogin extends Interpretador {
	
	private String[] dados;
	
	
	public String[] getDados() {
		return dados;
	}

	public DadosLogin(String[] dados) {
		this.dados = dados;
	}
}
