package br.feevale.droidhospital.db;

public class Paciente extends Interpretador {

	private static final long serialVersionUID = -6951261474636480115L;
	
	private Integer idPaciente;
	private String nomePaciente;
	private String leitoPaciente;
	private Integer quartoPaciente;
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getLeitoPaciente() {
		return leitoPaciente;
	}
	public void setLeitoPaciente(String leitoPaciente) {
		this.leitoPaciente = leitoPaciente;
	}
	public int getQuartoPaciente() {
		return quartoPaciente;
	}
	public void setQuartoPaciente(int quartoPaciente) {
		this.quartoPaciente = quartoPaciente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
