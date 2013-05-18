package br.feevale.droidhospital.db;

import java.util.ArrayList;
import java.util.Date;

public class PacienteDescription extends Interpretador {

	private String idPaciente;
	private String leito;
	private String quarto;
	private String nome;
	private Date dataEntrada;
	private Date dataNacimento;
	private String peso;
	private String alergias;
	private String fumante;
	private String idade;
	
	private ArrayList<Aplicacao> aplicacoesEfetuadas;
	private ArrayList<Aplicacao> aplicacoesFuturas;
	
	private static final long serialVersionUID = 2543119194938903557L;

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getLeito() {
		return leito;
	}

	public void setLeito(String leito) {
		this.leito = leito;
	}

	public String getQuarto() {
		return quarto;
	}

	public void setQuarto(String quarto) {
		this.quarto = quarto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getFumante() {
		return fumante;
	}

	public void setFumante(String fumante) {
		this.fumante = fumante;
	}

	public ArrayList<Aplicacao> getAplicacoesEfetuadas() {
		return aplicacoesEfetuadas;
	}

	public void setAplicacoesEfetuadas(ArrayList<Aplicacao> aplicacoesEfetuadas) {
		this.aplicacoesEfetuadas = aplicacoesEfetuadas;
	}

	public ArrayList<Aplicacao> getAplicacoesFuturas() {
		return aplicacoesFuturas;
	}

	public void setAplicacoesFuturas(ArrayList<Aplicacao> aplicacoesFuturas) {
		this.aplicacoesFuturas = aplicacoesFuturas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(Date dataNacimento) {
		this.dataNacimento = dataNacimento;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
<<<<<<< HEAD
}
=======

	
}
>>>>>>> Web Services Sync Tasks
