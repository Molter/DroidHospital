package br.feevale.droidhospital.db;


public class Prescricao extends Interpretador {

	private static final long serialVersionUID = -8062128180600282391L;
	
	private Integer idPrescricao;
	private Integer idAtendimento;
	private Integer idMedicamento;
	
	private Integer horaIntervaloAplicacoes, minutoIntervaloAplicacoes;
	
	private Integer horaInicioAplicacoes, minutoInicioAplicacoes;
	
	private Integer quantidadeAplicacoes;
	private Integer idMedico;
	public Integer getIdPrescricao() {
		return idPrescricao;
	}
	public void setIdPrescricao(Integer idPrescricao) {
		this.idPrescricao = idPrescricao;
	}
	public Integer getIdAtendimento() {
		return idAtendimento;
	}
	public void setIdAtendimento(Integer idAtendimento) {
		this.idAtendimento = idAtendimento;
	}
	public Integer getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public Integer getHoraIntervaloAplicacoes() {
		return horaIntervaloAplicacoes;
	}
	public void setHoraIntervaloAplicacoes(Integer horaIntervaloAplicacoes) {
		this.horaIntervaloAplicacoes = horaIntervaloAplicacoes;
	}
	public Integer getMinutoIntervaloAplicacoes() {
		return minutoIntervaloAplicacoes;
	}
	public void setMinutoIntervaloAplicacoes(Integer minutoIntervaloAplicacoes) {
		this.minutoIntervaloAplicacoes = minutoIntervaloAplicacoes;
	}
	public Integer getHoraInicioAplicacoes() {
		return horaInicioAplicacoes;
	}
	public void setHoraInicioAplicacoes(Integer horaInicioAplicacoes) {
		this.horaInicioAplicacoes = horaInicioAplicacoes;
	}
	public Integer getMinutoInicioAplicacoes() {
		return minutoInicioAplicacoes;
	}
	public void setMinutoInicioAplicacoes(Integer minutoInicioAplicacoes) {
		this.minutoInicioAplicacoes = minutoInicioAplicacoes;
	}
	public Integer getQuantidadeAplicacoes() {
		return quantidadeAplicacoes;
	}
	public void setQuantidadeAplicacoes(Integer quantidadeAplicacoes) {
		this.quantidadeAplicacoes = quantidadeAplicacoes;
	}
	public Integer getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}