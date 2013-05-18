package br.feevale.droidhospital.db;

import br.feevale.droidhospital.db.Interpretador;

public class DadosId extends Interpretador {
<<<<<<< HEAD

	private static final long serialVersionUID = -4792860943745432032L;
	private String idObjeto;

=======
	
	private static final long serialVersionUID = -4792860943745432032L;
	private String idObjeto;
	
	
>>>>>>> Web Services Sync Tasks
	public String getId() {
		return idObjeto;
	}

	public DadosId(String id) {
		this.idObjeto = id;
	}
}
