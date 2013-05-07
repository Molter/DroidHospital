package br.feevale.droidhospital.db;

public class Medicamento extends Interpretador {

	private static final long serialVersionUID = 5214888360118454517L;
	
	private Integer idMedicamento;
	private String farmaco;
	private String detentor;
	private String medicamentoReferencia;
	private String concentracao;
	private String formaFarmaceutica;
	
	public Integer getIdMedicamento() {
		return idMedicamento;
	}
	
	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	
	public String getFarmaco() {
		return farmaco;
	}
	
	public void setFarmaco(String farmaco) {
		this.farmaco = farmaco;
	}
	
	public String getDetentor() {
		return detentor;
	}
	
	public void setDetentor(String detentor) {
		this.detentor = detentor;
	}
	
	public String getMedicamentoReferencia() {
		return medicamentoReferencia;
	}
	
	public void setMedicamentoReferencia(String medicamentoReferencia) {
		this.medicamentoReferencia = medicamentoReferencia;
	}
	
	public String getConcentracao() {
		return concentracao;
	}
	
	public void setConcentracao(String concentracao) {
		this.concentracao = concentracao;
	}
	
	public String getFormaFarmaceutica() {
		return formaFarmaceutica;
	}
	
	public void setFormaFarmaceutica(String formaFarmaceutica) {
		this.formaFarmaceutica = formaFarmaceutica;
	}
}