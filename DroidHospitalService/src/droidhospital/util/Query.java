package droidhospital.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

public class Query {
	
	private String sql;
	private Conexao cnx;
	private HashMap<Integer, Object> parametros;
	
	public Query( Conexao cnx ) throws SQLException {
		
		parametros = new HashMap<>();
		
		this.cnx = cnx;
	}
	
	public void setSQL( String sql ) {
		this.sql = sql;
	}
	
	public void setSQL( StringBuilder query ) {
		setSQL( query.toString() );
	}
	
	public ResultSet executeQuery() throws SQLException {
		
		PreparedStatement ps = setaParametros();
		
		return ps.executeQuery();
	}
	
	public int executeUpdate() throws SQLException {
		
		PreparedStatement ps = setaParametros();
		
		return ps.executeUpdate();
	}
	
	public void setParameter( Integer parametro, Integer valor ) {
		parametros.put( parametro, valor );
	}
	
	public void setParameter( Integer parametro, String valor ) {
		parametros.put( parametro, valor );
	}
	
	public void setParameter( Integer parametro, java.util.Date valor ) {
		parametros.put( parametro, valor );
	}
	
	public String mostraQuery() throws SQLException {
		
		PreparedStatement ps = setaParametros();
		
		return ps.toString();
	}
	
	private PreparedStatement setaParametros() throws SQLException {
		
		PreparedStatement ps = cnx.getCnx().prepareStatement( sql );
		
		Set<Integer> chaves = parametros.keySet();
		
		for( Integer chave : chaves ) {
			
			if( chave != null ) {
				
				Object valor = parametros.get( chave );
				
				if( valor != null ) {
					
					if( valor instanceof Integer ) {
						ps.setInt( chave, (int) valor );
					} else if( valor instanceof String ) {
						ps.setString( chave, (String) valor );
					} else if( valor instanceof java.util.Date ) {
						ps.setDate( chave, getDateSQL( valor ) );
					} else {
						throw new SQLException( "Tipo de valor para o campo " + chave + " desconhecido." );
					}
					
				} else {
					ps.setObject( chave, null );
				}
			}
		}
		
		return ps;
	}

	private Date getDateSQL( Object data ) {
		
		java.util.Calendar cal = Calendar.getInstance();
		
		cal.setTime( (java.util.Date) data );
		
		System.out.println( "Hora: " + cal.getTime() );
		
		Date sqlDate = new Date( cal.getTime().getTime() );
		
		return sqlDate;
	}
}
