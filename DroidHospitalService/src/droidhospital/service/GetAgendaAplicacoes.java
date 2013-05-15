package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.feevale.droidhospital.db.Aplicacao;
import droidhospital.util.Conexao;
import droidhospital.util.Query;

public class GetAgendaAplicacoes extends Transacao {

	ArrayList<Aplicacao> aplicacoes;
	
	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {
	}

	@Override
	public void executaTransacao() {
		aplicacoes = new ArrayList<Aplicacao>();
		try {

			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append("SELECT * FROM aplicacoes a ");
			sbQuery.append(" INNER JOIN prescricoes p on a.idprescricao = p.idprescricao ");
			sbQuery.append(" INNER JOIN medicamentos m on p.idmedicamento = p.idmedicamento ");
			sbQuery.append(" INNER JOIN atendimentos atd on p.idatendimento = atd.idatendimento  ");
			sbQuery.append(" INNER JOIN pessoas ppl on atd.idpaciente = ppl.idpessoa  ");
			sbQuery.append(" INNER JOIN leitos l on l.idleito = atd.idleito  ");
			
			
			sbQuery.append(" WHERE ");
			sbQuery.append(" hora_aplicado is null ");
			//sbQuery.append(" and  ");
			//sbQuery.append(" atd.idpaciente =  " + dados.getId());
			
			sbQuery.append(" GROUP BY idaplicacao ");
			sbQuery.append(" ORDER BY hora_previsto ");
			
			
			
	        ResultSet resultSet = null;
	        Conexao cnx = new Conexao();
	        
	        System.out.println(sbQuery.toString());
	        try {
	        
				Query q = new Query( cnx );
				
				q.setSQL( sbQuery );
				
				resultSet = q.executeQuery();
				
				while( resultSet.next() ) {
					Aplicacao aplicacao = new Aplicacao();
					aplicacao.setIdAplicacao(resultSet.getInt("idAplicacao"));
					aplicacao.setIdEnfermeiro(resultSet.getInt("idEnfermeiro"));
					aplicacao.setConcentracaoMedicamento(resultSet.getString("concentracao"));
					aplicacao.setIdPrescricao(resultSet.getInt("idPrescricao"));
					aplicacao.setNomeMedicamento(resultSet.getString("farmaco"));
					aplicacao.setPrincipioAtivo("medicamento_referencia");
					aplicacao.setHoraPrevisto(resultSet.getDate("hora_previsto"));
					aplicacao.setNomePaciente(resultSet.getString("nome"));
					
					String quartoELeito = resultSet.getString("quarto") + " " + resultSet.getString("leito");
					
					aplicacao.setQuartoELeito(quartoELeito);
					aplicacoes.add(aplicacao);
				}
	        	
	        } catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				cnx.close();
				resultSet.close();  
			}
			
		} catch( Exception  e) {
			e.printStackTrace();
		}

	}

	@Override
	public Serializable getDadosResposta() {
		return aplicacoes;
	}

}
