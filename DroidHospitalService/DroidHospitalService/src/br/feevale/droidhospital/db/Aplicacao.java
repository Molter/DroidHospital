package br.feevale.droidhospital.db;

import java.util.Date;

public class Aplicacao extends Interpretador {
	
	private static final long serialVersionUID = -4877100379417254614L;
	
	private Integer idAplicacao;
	private Integer idEnfermeiro;
	private Integer idPrescricao;
	private Date horaPrevisto;
	private Date horaAplicado;
	
	public Integer getIdAplicacao() {
		return idAplicacao;
	}
	
	public void setIdAplicacao( Integer idAplicacao ) {
		this.idAplicacao = idAplicacao;
	}
	
	public Integer getIdEnfermeiro() {
		return idEnfermeiro;
	}
	
	public void setIdEnfermeiro( Integer idEnfermeiro ) {
		this.idEnfermeiro = idEnfermeiro;
	}
	
	public Integer getIdPrescricao() {
		return idPrescricao;
	}
	
	public void setIdPrescricao( Integer idPrescricao ) {
		this.idPrescricao = idPrescricao;
	}
	
	public Date getHoraPrevisto() {
		return horaPrevisto;
	}
	
	public void setHoraPrevisto( Date horaPrevisto ) {
		this.horaPrevisto = horaPrevisto;
	}
	
	public Date getHoraAplicado() {
		return horaAplicado;
	}
	
	public void setHoraAplicado( Date horaAplicado ) {
		this.horaAplicado = horaAplicado;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}