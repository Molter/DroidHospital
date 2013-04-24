package br.feevale.droidhospital.db;

import java.util.Date;

public class Prescricao extends Interpretador {

	private static final long serialVersionUID = -8062128180600282391L;
	
	private Integer idPrescricao;
	private Integer idAtendimento;
	private Integer idMedicamento;
	private Integer posologia;
	private Integer intervaloHoras;
	private Date horaInicio;
	private Integer quantidadeAplicacoes;
	private Integer idMedico;
	
	public Integer getIdPrescricao() {
		return idPrescricao;
	}
	
	public void setIdPrescricao( Integer idPrescricao ) {
		this.idPrescricao = idPrescricao;
	}
	
	public Integer getIdAtendimento() {
		return idAtendimento;
	}
	
	public void setIdAtendimento( Integer idAtendimento ) {
		this.idAtendimento = idAtendimento;
	}
	
	public Integer getIdMedicamento() {
		return idMedicamento;
	}
	
	public void setIdMedicamento( Integer idMedicamento ) {
		this.idMedicamento = idMedicamento;
	}
	
	public Integer getPosologia() {
		return posologia;
	}
	
	public void setPosologia( Integer posologia ) {
		this.posologia = posologia;
	}
	
	public Integer getIntervaloHoras() {
		return intervaloHoras;
	}
	
	public void setIntervaloHoras( Integer intervaloHoras ) {
		this.intervaloHoras = intervaloHoras;
	}
	
	public Date getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio( Date horaInicio ) {
		this.horaInicio = horaInicio;
	}
	
	public Integer getQuantidadeAplicacoes() {
		return quantidadeAplicacoes;
	}
	
	public void setQuantidadeAplicacoes( Integer quantidadeAplicacoes ) {
		this.quantidadeAplicacoes = quantidadeAplicacoes;
	}
	
	public Integer getIdMedico() {
		return idMedico;
	}
	
	public void setIdMedico( Integer idMedico ) {
		this.idMedico = idMedico;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}