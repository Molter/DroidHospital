package br.feevale.droidhospital.db;


public class MedicamentoDescription extends Interpretador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 883548177569131505L;
	private String idMedicamento;
	private String principio;
	private String laboratorio;
	private String fantasia;
	private String concentracao;
	private String forma;

	private String busca_medicamento;

	public String getBusca_medicamento() {
		System.out.println("buscaMedicamento: "+busca_medicamento);
		return busca_medicamento;
	}

	public MedicamentoDescription(String busca_medicamento) {
		this.busca_medicamento = busca_medicamento;
	}

	public String getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(String idMedicamento) {
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

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

}