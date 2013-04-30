package br.feevale.droidhospital.db;

import java.util.Date;

public class Atendimento extends Interpretador {

	private static final long serialVersionUID = -7937460562555250083L;
	
	private Integer idAtendimento;
	private Integer idLeito;
	private Integer idPaciente;
	private String fuma;
	private Double peso;
	private Date dataEntrada;
	private Date dataSaida;
	
	public Integer getIdAtendimento() {
		return idAtendimento;
	}
	
	public void setIdAtendimento( Integer idAtendimento ) {
		this.idAtendimento = idAtendimento;
	}
	
	public Integer getIdLeito() {
		return idLeito;
	}
	
	public void setIdLeito( Integer idLeito ) {
		this.idLeito = idLeito;
	}
	
	public Integer getIdPaciente() {
		return idPaciente;
	}
	
	public void setIdPaciente( Integer idPaciente ) {
		this.idPaciente = idPaciente;
	}
	
	public String getFuma() {
		return fuma;
	}
	
	public void setFuma( String fuma ) {
		this.fuma = fuma;
	}
	
	public Double getPeso() {
		return peso;
	}
	
	public void setPeso( Double peso ) {
		this.peso = peso;
	}
	
	public Date getDataEntrada() {
		return dataEntrada;
	}
	
	public void setDataEntrada( Date dataEntrada ) {
		this.dataEntrada = dataEntrada;
	}
	
	public Date getDataSaida() {
		return dataSaida;
	}
	
	public void setDataSaida( Date dataSaida ) {
		this.dataSaida = dataSaida;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}