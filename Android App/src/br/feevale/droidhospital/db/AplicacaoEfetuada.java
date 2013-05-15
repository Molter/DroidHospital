package br.feevale.droidhospital.db;

import java.util.Date;

public class AplicacaoEfetuada extends Interpretador {
	
	private static final long serialVersionUID = -4792860943745432032L;
	private String idObjeto;
	private Integer idEnfermeiro;
	private Date dataEfetuada;
	

	public String getId() {
		return idObjeto;
	}

	public AplicacaoEfetuada(String id) {
		this.idObjeto = id;
		setDataEfetuada(new Date());
	}

	public Date getDataEfetuada() {
		return dataEfetuada;
	}

	public void setDataEfetuada(Date dataEfetuada) {
		this.dataEfetuada = dataEfetuada;
	}

	public Integer getIdEnfermeiro() {
		return idEnfermeiro;
	}

	public void setIdEnfermeiro(Integer idEnfermeiro) {
		this.idEnfermeiro = idEnfermeiro;
	}

	
}
