package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;

import br.feevale.droidhospital.db.AplicacaoEfetuada;
import br.feevale.droidhospital.db.ConfirmaTransacao;
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
		if(!podeEdefuarAplicacao()){
			retorno.setResult(ConfirmaTransacao.RESULT_DENIED);
			return;
		}
		
		try {
			

			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append("UPDATE aplicacoes SET hora_aplicado = now() ");
			sbQuery.append(", idEnfermeiro = ");
			sbQuery.append(aplicacaoEfetuada.getIdEnfermeiro());
			sbQuery.append(" WHERE idaplicacao = ");
			sbQuery.append(aplicacaoEfetuada.getId());
			sbQuery.append(";");
	        Conexao cnx = new Conexao();
	        
	        System.out.println(sbQuery.toString());
	        try {
	        
				Query q = new Query( cnx );
				
				q.setSQL( sbQuery.toString());
				
				if(q.executeUpdate() > 0){
					retorno.setResult(ConfirmaTransacao.RESULT_OK);
				}else{
					retorno.setResult(ConfirmaTransacao.RESULT_FAIL);
				}
				
				
				
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

	private boolean podeEdefuarAplicacao() {
		
		boolean resultStatus = false;
	try {
			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append(" select * from aplicacoes ");
			sbQuery.append(" where idprescricao = (select idprescricao from aplicacoes where idaplicacao = " +String.valueOf(aplicacaoEfetuada.getId()) + ") ");
			sbQuery.append(" and hora_aplicado is null ");
			sbQuery.append(" and hora_previsto < (select hora_previsto from aplicacoes where idaplicacao = " +String.valueOf(aplicacaoEfetuada.getId()) + ") ");
			sbQuery.append(" order by hora_previsto");




	        Conexao cnx = new Conexao();
	        
	        System.out.println(sbQuery.toString());
	        try {
	        
				Query q = new Query( cnx );
				
				q.setSQL( sbQuery.toString());
				
				ResultSet result = q.executeQuery();
				
				if(result.next()){
					resultStatus = false;
				}else{
					resultStatus = true;
				}
				
			} finally {
				cnx.close();
			}
			
		} catch( Exception  e) {
			e.printStackTrace();
		}
		
		return resultStatus;
	}

	@Override
	public Serializable getDadosResposta() {
		return retorno;
	}
}
