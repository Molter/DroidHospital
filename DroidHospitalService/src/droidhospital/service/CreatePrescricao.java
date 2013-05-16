package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.feevale.droidhospital.db.Aplicacao;
import br.feevale.droidhospital.db.ConfirmaTransacao;
import br.feevale.droidhospital.db.Prescricao;
import droidhospital.util.Conexao;
import droidhospital.util.Query;

public class CreatePrescricao extends Transacao {

	private Prescricao prescricao;
	private Integer retorno;
	
	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {
		prescricao = (Prescricao) dadosRecebidos;
	}

	@Override
	public void executaTransacao() {
		
		try {

			Conexao cnx = new Conexao();
			
			try {
				
				inserePrescricao( cnx );
				
				ArrayList<Aplicacao> aplicacoes = getAplicacoes();
				
				for( Aplicacao aplicacao : aplicacoes ) {
					insereAplicacao( cnx, aplicacao );
				}
				
			} finally {
				
				retorno = ConfirmaTransacao.RESULT_OK;
				
				cnx.close();
			}
			
		} catch (ClassNotFoundException e) {
			retorno = ConfirmaTransacao.RESULT_FAIL;
			e.printStackTrace();
		} catch (SQLException e) {
			retorno = ConfirmaTransacao.RESULT_FAIL;
			e.printStackTrace();
		}
	}

	private void insereAplicacao( Conexao cnx, Aplicacao aplicacao ) throws SQLException {

		StringBuilder sbQuery = new StringBuilder();
		
		sbQuery.append( "INSERT INTO `droid_hospital`.`aplicacoes`\n" );
		sbQuery.append( "(`idEnfermeiro`,\n" );
		sbQuery.append( "`idprescricao`,\n" );
		sbQuery.append( "`hora_previsto`,\n" );
		sbQuery.append( "`hora_aplicado`)\n" );
		sbQuery.append( "VALUES\n" );
		sbQuery.append( "(\n" );
		sbQuery.append( "?,\n" );
		sbQuery.append( "?,\n" );
		sbQuery.append( "?,\n" );
		sbQuery.append( "?\n" );
		sbQuery.append( ");\n" );
		
		Query query = new Query( cnx );
		
		query.setSQL( sbQuery.toString() );
		
		query.setParameter( 1, aplicacao.getIdEnfermeiro() );
		query.setParameter( 2, aplicacao.getIdPrescricao() );
		query.setParameter( 3, aplicacao.getHoraPrevisto() );
		query.setParameter( 4, aplicacao.getHoraAplicado() );
		
		query.executeUpdate();
	}

	private ArrayList<Aplicacao> getAplicacoes() {
		
		ArrayList<Aplicacao> aplicacoes = new ArrayList<Aplicacao>();

		Calendar calendario = Calendar.getInstance();
		
		calendario.set( Calendar.HOUR_OF_DAY, prescricao.getHoraInicioAplicacoes() );
		calendario.set( Calendar.MINUTE, prescricao.getMinutoInicioAplicacoes() );
		calendario.set( Calendar.SECOND, 0 );
		calendario.set( Calendar.MILLISECOND, 0 );
		
		for( int i = 0; i < prescricao.getQuantidadeAplicacoes(); i++ ) {

			Aplicacao aplicacao = new Aplicacao();
			
			aplicacao.setIdEnfermeiro( null );
			aplicacao.setIdPrescricao( prescricao.getIdPrescricao() );
			aplicacao.setHoraPrevisto( calendario.getTime() );
			aplicacao.setHoraAplicado( null );
			
			deslocaData( calendario );
			
			aplicacoes.add( aplicacao );
		}
		
		return aplicacoes;
	}

	private void deslocaData(Calendar calendario) {
		
		calendario.add( Calendar.HOUR, prescricao.getHoraIntervaloAplicacoes() );
		calendario.add( Calendar.MINUTE, prescricao.getMinutoIntervaloAplicacoes() );
	}

	private void inserePrescricao( Conexao cnx ) throws SQLException {
		
		StringBuilder sbQuery = new StringBuilder();
		
		sbQuery.append( "INSERT INTO `droid_hospital`.`prescricoes`\n" );
		sbQuery.append( "(`idatendimento`,\n" );
		sbQuery.append( "`idmedicamento`,\n" );
		sbQuery.append( "`intervalo_aplicacoes`,\n" );
		sbQuery.append( "`hora_inicio`,\n" );
		sbQuery.append( "`quantidade_aplicacoes`,\n" );
		sbQuery.append( "`idMedico`)\n" );
		sbQuery.append( "VALUES\n" );
		sbQuery.append( "(\n" );
		sbQuery.append( "?,\n" );
		sbQuery.append( "?,\n" );
		sbQuery.append( "?,\n" );
		sbQuery.append( "?,\n" );
		sbQuery.append( "?,\n" );
		sbQuery.append( "?\n" );
		sbQuery.append( ");\n" );
		
		Query query = new Query( cnx );
		
		query.setSQL( sbQuery.toString() );

		query.setParameter( 1, prescricao.getIdAtendimento() );
		query.setParameter( 2, prescricao.getIdMedicamento() );
		
		String horarioAplicacao =  prescricao.getHoraIntervaloAplicacoes()+ ":"+ prescricao.getMinutoIntervaloAplicacoes();
		query.setParameter( 3, horarioAplicacao);
		
		String inicioAplicacao =  prescricao.getHoraInicioAplicacoes()+ ":"+ prescricao.getMinutoInicioAplicacoes();
		query.setParameter( 4, inicioAplicacao);
		
		query.setParameter( 5, prescricao.getQuantidadeAplicacoes() );
		query.setParameter( 6, prescricao.getIdMedico() );
		
		query.executeUpdate();
		
		sbQuery = new StringBuilder();
		
		sbQuery.append( "SELECT MAX( `prescricoes`.`idprescricao` ) FROM `droid_hospital`.`prescricoes` WHERE `prescricoes`.`idatendimento` = " );
		sbQuery.append( prescricao.getIdAtendimento() );
		sbQuery.append( " AND `prescricoes`.`idmedicamento` = " );
		sbQuery.append( prescricao.getIdMedicamento() );
		sbQuery.append( ";" );
		
		query = new Query( cnx );
		
		query.setSQL( sbQuery.toString() );
		
		ResultSet resultSet = query.executeQuery();
		
		if( resultSet.first() ) {
			prescricao.setIdPrescricao( resultSet.getInt( 1 ) );
		}
	}

	@Override
	public Serializable getDadosResposta() {
		
		ConfirmaTransacao transaction = new ConfirmaTransacao();
		
		transaction.setResult( retorno ) ;
		
		return transaction;
	}
}
