package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.feevale.droidhospital.db.Aplicacao;
import br.feevale.droidhospital.db.AplicacaoEfetuada;
import br.feevale.droidhospital.db.ConfirmaTransacao;
import br.feevale.droidhospital.db.DadosId;
import droidhospital.util.Conexao;
import droidhospital.util.Query;

public class EfetuaAplicacao extends Transacao {

	AplicacaoEfetuada aplicacaoEfetuada;
	ConfirmaTransacao retorno = new ConfirmaTransacao();
	
	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {
		aplicacaoEfetuada = (AplicacaoEfetuada) dadosRecebidos;
	}

	@Override
	public void executaTransacao() {
		try {

			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append("UPDATE aplicacoes SET hora_aplicado = now() where idaplicacao = ");
			sbQuery.append(aplicacaoEfetuada.getId());
			sbQuery.append(";");
	        Conexao cnx = new Conexao();
	        
	        System.out.println(sbQuery.toString());
	        try {
	        
				Query q = new Query( cnx );
				
				q.setSQL( sbQuery.toString());
				
					
				retorno.setResult(ConfirmaTransacao.RESULT_OK);
				
				
	        } catch (Exception e) {
				e.printStackTrace();
				retorno.setResult(ConfirmaTransacao.RESULT_FAIL);
				
			} finally {
				cnx.close();
			}
			
		} catch( Exception  e) {
			e.printStackTrace();
		}

	}

	@Override
	public Serializable getDadosResposta() {
		return retorno;
	}
}