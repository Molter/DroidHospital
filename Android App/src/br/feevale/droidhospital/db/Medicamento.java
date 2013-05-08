package br.feevale.droidhospital.db;

public class Medicamento extends Interpretador {

	private static final long serialVersionUID = 5214888360118454517L;
	
	private Integer idMedicamento;
	private String principio;
	private String laboratorio;
	private String fantasia;
	private String concentracao;
	private String formaFarmaceutica;
	
	public Integer getIdMedicamento() {
		return idMedicamento;
	}
	
	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	
	public String getPrincipio() {
		return principio;
	}
	
	public void setPrincipio(String principio) {
		this.principio = principio;
	}
	
	public String getLaboratorio() {
		return laboratorio;
	}
	
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	public String getFantasia() {
		return fantasia;
	}
	
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
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