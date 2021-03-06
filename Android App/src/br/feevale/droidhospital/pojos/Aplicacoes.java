package br.feevale.droidhospital.pojos;

import java.util.ArrayList;


public class Aplicacoes {
	ArrayList<AplicacaoFake> aplicacoes;
	
	public Aplicacoes() {
		setUpAplicacoes();
	}
	
	private void setUpAplicacoes() {
		aplicacoes = new ArrayList<AplicacaoFake>();
		
		aplicacoes.add(new AplicacaoFake());
		aplicacoes.add(new AplicacaoFake());
		aplicacoes.add(new AplicacaoFake());
		aplicacoes.add(new AplicacaoFake());
		aplicacoes.add(new AplicacaoFake());
		aplicacoes.add(new AplicacaoFake());
		aplicacoes.add(new AplicacaoFake());
	}

	public int countAplicacaoesEfetuadas() {
		int total = 0;
		for (int i = 0; i < aplicacoes.size(); i++) {
			if(aplicacoes.get(i).isAplicada()){
				total++;
			}
		}
		return total;
	}
	
	public int countAplicacaoesFuturas() {
		int total = 0;
		for (int i = 0; i < aplicacoes.size(); i++) {
			if(!aplicacoes.get(i).isAplicada()){
				total++;
			}
		}
		return total;
	}

	public ArrayList<AplicacaoFake> getAplicacoes() {
		return aplicacoes;
	}

	public void setAplicacoes(ArrayList<AplicacaoFake> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}
}
