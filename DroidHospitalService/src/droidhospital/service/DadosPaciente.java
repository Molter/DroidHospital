package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.feevale.droidhospital.db.Aplicacao;
import br.feevale.droidhospital.db.DadosId;
import br.feevale.droidhospital.db.PacienteDescription;
import droidhospital.util.Conexao;
import droidhospital.util.Extenso;
import droidhospital.util.Query;

public class DadosPaciente extends Transacao {

	String idPaciente;
	PacienteDescription pacienteDescription;
	
	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {
		System.out.println("set dados recebidos");
		DadosId dadosId = (DadosId) dadosRecebidos;
		idPaciente = dadosId.getId();
	}


	
	@Override
	public void executaTransacao() {
		System.out.println("Criando Paciente Descripton...");
		createPacientDescription();
		System.out.println("setUpAplicacoesEfetuadas...");
		setUpAplicacoesEfetuadas();
		System.out.println("setUpAplicacoesFuturas ...");
		setUpAplicacoesFuturas();
	}
	


	public void createPacientDescription() {

		pacienteDescription = new PacienteDescription();
		
		try {

			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append(  "select ");
			sbQuery.append( " p.idpessoa, p.nome, a.data_entrada, a.fuma, a.peso, p.data_nascimento, l.quarto, l.leito, p.alergias ");
			sbQuery.append(" from atendimentos a");
			sbQuery.append(" inner join pessoas  p on a.idpaciente = p.idpessoa");
			sbQuery.append(" inner join leitos  l on l.idleito = a.idleito");
			sbQuery.append(" where idpessoa = " + idPaciente + ";");
			
	        ResultSet resultSet = null;
	        Conexao cnx = new Conexao();
	        

	        System.out.println(sbQuery.toString());

	        try {
	        
				Query q = new Query( cnx );
				
				q.setSQL( sbQuery );
				
				resultSet = q.executeQuery();
				
				while( resultSet.next() ) {

					pacienteDescription.setIdPaciente(idPaciente);
					pacienteDescription.setNome(resultSet.getString("nome"));
					pacienteDescription.setFumante(resultSet.getString("fuma"));
					pacienteDescription.setPeso(resultSet.getString("peso"));

					pacienteDescription.setQuarto(resultSet.getString("quarto"));
					pacienteDescription.setLeito(resultSet.getString("leito"));
					pacienteDescription.setAlergias(resultSet.getString("alergias"));
					
					//data entrada
					pacienteDescription.setDataEntrada(resultSet.getDate("data_entrada"));
					
					Date dataNascimento = resultSet.getDate("data_nascimento");
					
					Calendar hoje = Calendar.getInstance();
					
					Calendar dataNascimentoCalendar = new GregorianCalendar();
					dataNascimentoCalendar.setTime(dataNascimento);
					
					int idade = hoje.get(Calendar.YEAR) - dataNascimentoCalendar.get(Calendar.YEAR);
					if (hoje.before(dataNascimentoCalendar)) {
			            idade--;
			        }
					
					String anosString;
					if(idade == 1) {
						anosString = " ano";
					}else {
						anosString = " anos";
					}
					String anosExtenso = new Extenso(idade).toString() + anosString;
					
					int meses;
					String mesesExtenso = "";
					
					if( hoje.get(Calendar.MONTH) > dataNascimentoCalendar.get(Calendar.MONTH)) {
						meses = hoje.get(Calendar.MONTH) - dataNascimentoCalendar.get(Calendar.MONTH);
						mesesExtenso = " e " + new Extenso(meses).toString() + " meses";
						
					}
					
					if( hoje.get(Calendar.MONTH) < dataNascimentoCalendar.get(Calendar.MONTH)) {
						meses = dataNascimentoCalendar.get(Calendar.MONTH) - hoje.get(Calendar.MONTH);
						mesesExtenso = " e " + new Extenso(meses).toString() + " meses";
					}
					pacienteDescription.setDataNacimento(dataNascimento);
					pacienteDescription.setIdade(anosExtenso + mesesExtenso);
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

		System.out.println("returning Data ...");

		return pacienteDescription;
	}
	
	private void setUpAplicacoesFuturas() {

		
		ArrayList<Aplicacao> aplicacoesEfetuadas = getAplicacoes( true );
		
		pacienteDescription.setAplicacoesEfetuadas(aplicacoesEfetuadas);
	}

	private void setUpAplicacoesEfetuadas() {
		
		ArrayList<Aplicacao> aplicacoesFuturas = getAplicacoes( false );
		
		pacienteDescription.setAplicacoesFuturas(aplicacoesFuturas);
	}
	
	private ArrayList<Aplicacao> getAplicacoes(boolean efetuadas) {
		
		ArrayList<Aplicacao> aplicacoes = new ArrayList<Aplicacao>();
		
		try {

			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append("select ap.*, me.*, ap.hora_aplicado as date_aplicado, ap.hora_previsto as date_previsto ");
			sbQuery.append(" , hour(hora_previsto) as hora_previsto, minute(hora_previsto) as minuto_previsto, hour(hora_aplicado) as hora_aplicado, minute(hora_aplicado) as minute_aplicado ");
			sbQuery.append(" from atendimentos at, prescricoes pr, aplicacoes ap, medicamentos me ");
			sbQuery.append(" where me.idmedicamento = pr.idmedicamento and pr.idatendimento = at.idatendimento and ap.idprescricao = pr.idprescricao and at.idpaciente = ");
			sbQuery.append(idPaciente);
			sbQuery.append(" and ap.hora_aplicado is ");
			
			if( efetuadas ) {
				sbQuery.append(" not ");
			}
			
			sbQuery.append(" null order by ap.idaplicacao desc limit 5;");
			
			System.out.println(sbQuery.toString());
			
	        ResultSet resultSet = null;
	        Conexao cnx = new Conexao();
	        
	        try {
	        
				Query q = new Query( cnx );
				
				q.setSQL( sbQuery );
				
				resultSet = q.executeQuery();
				
				while( resultSet.next() ) {
					
					Aplicacao aplicacao = new Aplicacao();
					
					aplicacao.setConcentracaoMedicamento( resultSet.getString( "concentracao" ) );
					
					//hora aplicado
					Calendar calendar = Calendar.getInstance();
					
					Date dateAplicado = resultSet.getDate( "date_aplicado" );
					if(dateAplicado != null) {
						calendar.setTime(dateAplicado);
						calendar.set(Calendar.HOUR_OF_DAY, resultSet.getInt("hora_aplicado"));
						calendar.set(Calendar.MINUTE, resultSet.getInt("minuto_aplicado"));
						aplicacao.setHoraAplicado(calendar.getTime());
					}
					
					//hora previsto
					Date dataPrevisto = resultSet.getDate( "date_previsto" );
					if(dataPrevisto != null) {
						calendar.setTime(dataPrevisto);
						calendar.set(Calendar.HOUR_OF_DAY, resultSet.getInt("hora_previsto"));
						calendar.set(Calendar.MINUTE, resultSet.getInt("minuto_previsto"));
						aplicacao.setHoraPrevisto(calendar.getTime());
					}
					
					
					aplicacao.setIdAplicacao( resultSet.getInt( "idaplicacao" ) );
					aplicacao.setIdEnfermeiro( resultSet.getInt( "idEnfermeiro" ) );
					aplicacao.setIdPrescricao( resultSet.getInt( "idprescricao" ) );
					aplicacao.setNomeMedicamento( resultSet.getString( "farmaco" ) );
					aplicacao.setPrincipioAtivo( resultSet.getString( "farmaco" ) );
					
					aplicacoes.add( aplicacao );
				}
	        	
			} finally {
				
				cnx.close();
				resultSet.close();  
			}
			
		} catch( Exception  e) {
			e.printStackTrace();
		}
		
		return aplicacoes;
	}

}
