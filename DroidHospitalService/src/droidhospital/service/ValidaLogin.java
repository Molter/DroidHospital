package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;

import br.feevale.droidhospital.db.DadosLogin;
import br.feevale.droidhospital.db.DadosUsuario;
import droidhospital.util.Conexao;
import droidhospital.util.Query;

public class ValidaLogin extends Transacao {

	
	private DadosLogin dados;
	private DadosUsuario dadosUsuario;
	
	
	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {
		dados = (DadosLogin) dadosRecebidos;
		dadosUsuario = new DadosUsuario();
	}

	@Override
	public void executaTransacao() {

		try {

			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append( "SELECT idpessoa, tipo_Pessoa FROM pessoas WHERE usuario = '" );
			sbQuery.append( dados.getDados()[0] );
			sbQuery.append( "' AND senha = '" );
			sbQuery.append( dados.getDados()[1] );
			sbQuery.append( "' LIMIT 1;" );
			
			System.out.println(sbQuery.toString());
			ResultSet resultSet = null;
	        Conexao cnx = new Conexao();
			
			try {
				
				Query q = new Query( cnx );
				
				q.setSQL( sbQuery );
				
				resultSet = q.executeQuery();
				
				if( resultSet.first() ) {
					
					dadosUsuario.setTipoUsuario(resultSet.getString( "tipo_Pessoa" ));
					dadosUsuario.setIdUsuario(resultSet.getInt("idpessoa")); 
				} else {
					dadosUsuario.setTipoUsuario(DadosUsuario.FAIL);
					dadosUsuario.setIdUsuario(0);
				}
				
			} finally {
				
				cnx.close();
				resultSet.close();  
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public Serializable getDadosResposta() {
		return dadosUsuario;
	}
}