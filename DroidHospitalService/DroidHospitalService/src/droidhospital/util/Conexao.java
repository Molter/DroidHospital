package droidhospital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private Connection cnx;
	
	public Conexao() throws ClassNotFoundException, SQLException {
		
		Class.forName( "com.mysql.jdbc.Driver" );
		
		cnx = DriverManager.getConnection( "jdbc:mysql://" + Parametros.IP_BANCO + ":" + Parametros.PORTA_BANCO + "/" + Parametros.NOME_BANCO, Parametros.USUARIO_BANCO, Parametros.SENHA_BANCO );
	}
	
	public Connection getCnx() {
		return cnx;
	}
	
	public void close() throws SQLException {
		cnx.close();
	}
}