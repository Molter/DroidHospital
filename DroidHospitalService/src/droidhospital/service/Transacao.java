package droidhospital.service;

import java.io.Serializable;

import br.feevale.droidhospital.db.Interpretador;

public abstract class Transacao {
	
	public abstract void setDadosRecebidos( Serializable dadosRecebidos );
	public abstract void executaTransacao();
	public abstract Serializable getDadosResposta();

	public static Transacao verificaTransacao( Serializable dadosRecebidos ) throws DroidHospitalException {
		
    	Transacao transacao = null;

    	Interpretador interpretador = (Interpretador) dadosRecebidos;
    	
    	if( interpretador != null ) {
    		
    		if( interpretador.getCdTransacao() != null ) {
    			
    			switch( interpretador.getCdTransacao() ) {

					case Interpretador.VALIDA_LOGIN:
					
						transacao = new ValidaLogin();
						break;
    			
					case Interpretador.LISTA_LEITOS:
						
						transacao = new ListaLeitos();
						break;
					case Interpretador.LISTA_PACIENTES:
						
						transacao = new ListaPacientes();
						break;
					case Interpretador.DADOS_PACIENTE:
						
						transacao = new DadosPaciente();
						break;
						
					case Interpretador.LISTA_PESSOAS:
						
						break;
						
					case Interpretador.LISTA_MEDICAMENTOS:
						
						transacao = new ListaMedicamentos();
						
						break;
						
					case Interpretador.BUSCA_MEDICAMENTO:
						
						transacao = new BuscaMedicamento();
						
						break;
						
					case Interpretador.CREATE_PRESCRICAO:
						
						transacao = new CreatePrescricao();
						
						break;
					case Interpretador.LISTA_APLICACOES:
						transacao = new GetAplicacoes();
						
						break;	
						
					case Interpretador.ENVIA_APLICACAO:
						transacao = new EfetuaAplicacao();
						
						break;
					
						
					default:
						throw new DroidHospitalException( "C�digo de transa��o (" + interpretador.getCdTransacao() + ") desconhecido." );
				}
    			
    		} else {
    			throw new DroidHospitalException( "C�digo de transa��o (" + interpretador.getCdTransacao() + ") desconhecido." );
    		}
    		
    	} else {
    		throw new DroidHospitalException( "Imposs�vel interpretar dados recebidos." );
    	}
    	
       	transacao.setDadosRecebidos( dadosRecebidos );

    	return transacao;
	}
}