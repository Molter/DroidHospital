package droidhospital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
	
	private Statement statement; 
	private String sql;
	
	public Query( Conexao cnx ) throws SQLException {
		statement = cnx.getCnx().createStatement();
	}
	
	public void setSQL( String sql ) {
		this.sql = sql;
	}
	
	public void setSQL( StringBuilder query ) {
		setSQL( query.toString() );
	}
	
	public ResultSet executeQuery() throws SQLException {
		return statement.executeQuery( sql );
	}
	
	public int executeUpdate() throws SQLException {
		return statement.executeUpdate( sql );
	}
}