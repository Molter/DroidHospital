package droidhospital.service;

import java.io.Serializable;

import br.feevale.droidhospital.db.ConfirmaTransacao;
import br.feevale.droidhospital.db.Prescricao;

public class CreatePrescricao extends Transacao {

	private Prescricao prescricao;
	
	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {
		prescricao = (Prescricao) dadosRecebidos;
	}

	@Override
	public void executaTransacao() {
		// TODO Auto-generated method stub

	}

	@Override
	public Serializable getDadosResposta() {
		ConfirmaTransacao transaction = new ConfirmaTransacao();
		
		transaction.setResult(ConfirmaTransacao.RESULT_FAIL);
		
		return transaction;
	}

}
