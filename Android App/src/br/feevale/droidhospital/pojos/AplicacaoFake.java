package br.feevale.droidhospital.pojos;

import java.util.Random;

public class AplicacaoFake {
	private String dataHoraAplicacao;
	private String nomeMedicamento;
	private String dosagem;
	private boolean aplicada;
	
	private long  idPaciente;
	
	public AplicacaoFake() {
		
		Random random = new Random();
		aplicada = random.nextBoolean();
		
		dataHoraAplicacao = "22/03/2013 18:00";
		nomeMedicamento = "Paracetamol ";
		dosagem = String.valueOf(random.nextInt(1000)) + "mg";
		
	}
	
	public String getDataHoraAplicacao() {
		return dataHoraAplicacao;
	}
	public void setDataHoraAplicacao(String dataHoraAplicacao) {
		this.dataHoraAplicacao = dataHoraAplicacao;
	}
	public String getNomeMedicamento() {
		return nomeMedicamento;
	}
	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}
	public String getDosagem() {
		return dosagem;
	}
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}
	public boolean isAplicada() {
		return aplicada;
	}
	public void setAplicada(boolean aplicada) {
		this.aplicada = aplicada;
	}
	public long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}
	
}
