package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.feevale.droidhospital.db.DadosId;
import br.feevale.droidhospital.db.Paciente;
import droidhospital.util.Conexao;
import droidhospital.util.Query;

public class ListaPacientes extends Transacao {

	private ArrayList<Paciente> pacientes;
	private DadosId dados;
	
	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {
		dados = (DadosId) dadosRecebidos;
	}

	@Override
	public void executaTransacao() {

		pacientes = new ArrayList<Paciente>();
		
		try {
				
			StringBuilder sbQuery = new StringBuilder();
			
			String numeroQuarto = dados.getId();
			
			sbQuery.append( " select p.idpessoa, l.quarto, l.leito, p.nome, a.idatendimento " +
							" from atendimentos a " +
							" inner join leitos  l on a.idleito = l.idleito " +
							" inner join pessoas p on a.idpaciente = p.idpessoa " +
							" where data_saida is null" +
							" and l.quarto = " + numeroQuarto);
			//sbQuery.append( "  SELECT DISTINCT idLeito FROM Atendimentos WHERE data_saida IS NULL " );
			//sbQuery.append( ");" );
			//sbQuery.append( "  SELECT DISTINCT idLeito FROM Atendimentos WHERE data_saida IS NULL " );
			//sbQuery.append( ");" );
			
	        ResultSet resultSet = null;
	        Conexao cnx = new Conexao();
	        
	        System.out.println(sbQuery.toString());
	        try {
	        
				Query q = new Query( cnx );
				
				q.setSQL( sbQuery );
				
				resultSet = q.executeQuery();
				
				while( resultSet.next() ) {
					
					Paciente paciente = new Paciente();
					
					paciente.setIdPaciente(resultSet.getInt("idpessoa"));
					paciente.setQuartoPaciente(resultSet.getInt("quarto"));
					paciente.setLeitoPaciente(resultSet.getString("leito"));
					paciente.setNomePaciente(resultSet.getString("nome"));
					paciente.setIdatendimento(resultSet.getInt("idatendimento"));
					
					pacientes.add( paciente );
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
		return pacientes;
	}
}