package br.feevale.droidhospital.db;

import br.feevale.droidhospital.db.Interpretador;

public class DadosId extends Interpretador {
	
	private static final long serialVersionUID = -4792860943745432032L;

	private String idObjeto;

	public String getId() {
		return idObjeto;
	}

	public DadosId(String id) {
		this.idObjeto = id;
	}
}
