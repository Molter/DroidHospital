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
		
		createPacientDescription();
		setUpAplicacoesEfetuadas();
		setUpAplicacoesFuturas();
	}
	
	public void createPacientDescription() {
		
		pacienteDescription = new PacienteDescription();
		
		try {

			StringBuilder sbQuery = new StringBuilder();
			
			sbQuery.append("select ");
			sbQuery.append(" p.idpessoa, p.nome, a.data_entrada, a.fuma, a.peso, p.data_nascimento, l.quarto, l.leito, p.alergias ");
			sbQuery.append(" from atendimentos a");
			sbQuery.append(" inner join pessoas  p on a.idpaciente = p.idpessoa");
			sbQuery.append(" inner join leitos  l on l.idleito = a.idleito");
			sbQuery.append(" where idpessoa = " + idPaciente + ";");
			
	        ResultSet resultSet = null;
	        Conexao cnx = new Conexao();
	        
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
			
			sbQuery.append("select ap.*, me.* from atendimentos at, prescricoes pr, aplicacoes ap, medicamentos me ");
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
					aplicacao.setHoraAplicado( resultSet.getDate( "hora_aplicado" ) );
					aplicacao.setHoraPrevisto( resultSet.getDate( "hora_previsto" ) );
					aplicacao.setIdAplicacao( resultSet.getInt( "idaplicacao" ) );
					aplicacao.setIdEnfermeiro( resultSet.getInt( "idEnfermeiro" ) );
					aplicacao.setIdPrescricao( resultSet.getInt( "idprescricao" ) );
					aplicacao.setNomeMedicamento( resultSet.getString( "farmaco" ) );
					aplicacao.setPrincipioAtivo( resultSet.getString( "farmaco" ) ); // TODO ver isso
					
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