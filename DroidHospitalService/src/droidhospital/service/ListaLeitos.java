package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.feevale.droidhospital.db.Leito;
import droidhospital.util.Conexao;
import droidhospital.util.Query;

public class ListaLeitos extends Transacao {

	private ArrayList<Leito> leitos;
	
	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {}

	@Override
	public void executaTransacao() {

		leitos = new ArrayList<Leito>();
		
		try {

			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append( "SELECT * FROM Leitos WHERE idLeito IN ( " );
			sbQuery.append( "  SELECT DISTINCT idLeito FROM Atendimentos WHERE data_saida IS NULL " );
			sbQuery.append( ");" );
			
	        ResultSet resultSet = null;
	        Conexao cnx = new Conexao();
	        
	        try {
	        
				Query q = new Query( cnx );
				
				q.setSQL( sbQuery );
				
				resultSet = q.executeQuery();
				
				while( resultSet.next() ) {
					
					Leito leito = new Leito();
					
					leito.setIdLeito( resultSet.getInt( "idLeito" ) );
					leito.setLeito( resultSet.getString( "leito" ) );
					leito.setQuarto( resultSet.getString( "quarto" ) );
					
					leitos.add( leito );
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
		return leitos;
	}
}