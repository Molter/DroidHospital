package br.feevale.droidhospital.pojos;

public class Prescricao {

	int    id;
	String principio;
	String referencia;
	String laboratorio;
	String concentracao;
	String posologia;
	String hora_inicial;
	String qtd_aplicacoes;
	String intervalo;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPrincipio() {
		return principio;
	}
	
	public void setPrincipio(String principio) {
		this.principio = principio;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getLaboratorio() {
		return laboratorio;
	}
	
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	public String getConcentracao() {
		return concentracao;
	}
	
	public void setConcentracao(String concentracao) {
		this.concentracao = concentracao;
	}
	
	public String getPosologia() {
		return posologia;
	}
	
	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}
	
	public String getHora_inicial() {
		return hora_inicial;
	}
	
	public void setHora_inicial(String hora_inicial) {
		this.hora_inicial = hora_inicial;
	}
	
	public String getQtd_aplicacoes() {
		return qtd_aplicacoes;
	}
	
	public void setQtd_aplicacoes(String qtd_aplicacoes) {
		this.qtd_aplicacoes = qtd_aplicacoes;
	}
	
	public String getIntervalo() {
		return intervalo;
	}
	
	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}	
	
}
