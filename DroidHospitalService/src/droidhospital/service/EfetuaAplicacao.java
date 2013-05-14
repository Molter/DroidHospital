package droidhospital.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
		try {

			StringBuilder sbQuery = new StringBuilder();
			
			Date dataEfetuada = aplicacaoEfetuada.getDataEfetuada();
			Calendar c = Calendar.getInstance();
			c.setTime(dataEfetuada);
			
			StringBuilder dateRecord = new StringBuilder();
			
			dateRecord.append(c.get(Calendar.YEAR));
			dateRecord.append("-");
			dateRecord.append(c.get(Calendar.MONTH));
			dateRecord.append("-");
			dateRecord.append(c.get(Calendar.DAY_OF_MONTH));
			dateRecord.append(" ");
			
			dateRecord.append(c.get(Calendar.HOUR_OF_DAY));
			dateRecord.append(":");
			dateRecord.append(c.get(Calendar.MINUTE));
			dateRecord.append(":");
			dateRecord.append(c.get(Calendar.SECOND));
			
			
			sbQuery.append("UPDATE aplicacoes SET hora_aplicado = \"");
			sbQuery.append(dateRecord.toString());
			sbQuery.append("\"");
			
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

	@Override
	public Serializable getDadosResposta() {
		return retorno;
	}
}
