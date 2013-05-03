package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.feevale.droidhospital.db.Quarto;
import droidhospital.util.Conexao;
import droidhospital.util.Query;

public class ListaLeitos extends Transacao {

	private ArrayList<Quarto> Quartos;
	
	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {}

	@Override
	public void executaTransacao() {

		Quartos = new ArrayList<Quarto>();
		
		try {

			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append( "select distinct quarto from leitos order by quarto, leito ;" );
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
					
					Quarto leito = new Quarto();
					leito.setQuarto( resultSet.getString( "quarto" ) );
					Quartos.add( leito );
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
		return Quartos;
	}
}