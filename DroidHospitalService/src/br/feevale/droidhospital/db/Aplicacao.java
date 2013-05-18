package br.feevale.droidhospital.db;

import java.util.Date;

public class Aplicacao extends Interpretador {
	
	private static final long serialVersionUID = -4877100379417254614L;
	
	private Integer idAplicacao;
	private Integer idEnfermeiro;
	private Integer idPrescricao;
	private Date horaPrevisto;
	private Date horaAplicado;
	
	private String concentracaoMedicamento;
	private String nomeMedicamento;
	private String principioAtivo;
	
<<<<<<< HEAD
	private boolean aplicada = false;
	
	private String nomePaciente;
	private String QuartoELeito;
	
=======
>>>>>>> Web Services Sync Tasks
	public Integer getIdAplicacao() {
		return idAplicacao;
	}
	
	public void setIdAplicacao( Integer idAplicacao ) {
		this.idAplicacao = idAplicacao;
	}
	
	public Integer getIdEnfermeiro() {
		return idEnfermeiro;
	}
	
	public void setIdEnfermeiro( Integer idEnfermeiro ) {
		this.idEnfermeiro = idEnfermeiro;
	}
	
	public Integer getIdPrescricao() {
		return idPrescricao;
	}
	
	public void setIdPrescricao( Integer idPrescricao ) {
		this.idPrescricao = idPrescricao;
	}
	
	public Date getHoraPrevisto() {
		return horaPrevisto;
	}
	
	public void setHoraPrevisto( Date horaPrevisto ) {
		this.horaPrevisto = horaPrevisto;
	}
	
	public Date getHoraAplicado() {
		return horaAplicado;
	}
	
	public void setHoraAplicado( Date horaAplicado ) {
		this.horaAplicado = horaAplicado;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getConcentracaoMedicamento() {
		return concentracaoMedicamento;
	}

	public void setConcentracaoMedicamento(String concentracaoMedicamento) {
		this.concentracaoMedicamento = concentracaoMedicamento;
	}

	public String getNomeMedicamento() {
		return nomeMedicamento;
	}

	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}
<<<<<<< HEAD

	public boolean isAplicada() {
		return aplicada;
	}

	public void setAplicada(boolean aplicada) {
		this.aplicada = aplicada;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getQuartoELeito() {
		return QuartoELeito;
	}

	public void setQuartoELeito(String quartoELeito) {
		QuartoELeito = quartoELeito;
	}
=======
>>>>>>> Web Services Sync Tasks
}