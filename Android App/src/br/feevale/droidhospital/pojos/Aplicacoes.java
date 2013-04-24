package br.feevale.droidhospital.pojos;

import java.util.ArrayList;


public class Aplicacoes {
	ArrayList<Aplicacao> aplicacoes;
	
	Paciente paciente;
	
	public Aplicacoes(long pacienteId) {
		paciente = Paciente.getPacienteById(pacienteId);
		setUpAplicacoes();
	}
	
	private void setUpAplicacoes() {
		aplicacoes = new ArrayList<Aplicacao>();
		
		aplicacoes.add(new Aplicacao(1));
		aplicacoes.add(new Aplicacao(1));
		aplicacoes.add(new Aplicacao(1));
		aplicacoes.add(new Aplicacao(1));
		aplicacoes.add(new Aplicacao(1));
		aplicacoes.add(new Aplicacao(1));
		aplicacoes.add(new Aplicacao(1));
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

	public ArrayList<Aplicacao> getAplicacoes() {
		return aplicacoes;
	}

	public void setAplicacoes(ArrayList<Aplicacao> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
	
}
