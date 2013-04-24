package br.feevale.droidhospital.pojos;

import java.util.ArrayList;

public class Paciente {
	String nome;
	long id;
	long idQuarto;
	int peso;
	private Quarto quarto;
	private String idade;
	private String dataDeInternacao;
	private boolean fumante;
	private String alergias;
	
	public static ArrayList<Paciente> pacientes = new ArrayList<Paciente>(){
		{
			add(new Paciente(1,"Marco",1));
			add(new Paciente(2,"Joao",1));
			add(new Paciente(3,"Maria",2));
			add(new Paciente(4,"Marcos",2));
			add(new Paciente(5,"Lauro",3));
			add(new Paciente(6,"Paulo",3));
			add(new Paciente(7,"Joao",4));
			add(new Paciente(8,"Jesse",4));
		}
	};
	
	public static ArrayList<Paciente> getPacientesByIdQuarto(long id) {
		ArrayList<Paciente> returned = new ArrayList<Paciente>();
		for (int i = 0; i < pacientes.size(); i++) {
			if(pacientes.get(i).getIdQuarto() == id) {
				returned.add(pacientes.get(i));
			}
		}
		return returned;
	}
	
	public static Paciente getPacienteById(long id) {
		for (int i = 0; i < pacientes.size(); i++) {
			if(pacientes.get(i).getId() == id){
				return pacientes.get(i);
			}
		}
		return null;
	}
	
	public Paciente(long id, String nome, long idQuarto) {
		this.nome =  nome;
		this.id =  id;
		this.idQuarto =  idQuarto;
		this.quarto = Quarto.getQuartoById(this.idQuarto);
		this.idade = "22 anos e 11 meses";
		this.dataDeInternacao = "22/01/2013";
		this.fumante = false;
		this.peso = 95;
		this.alergias = "nenhuma";
	}
	
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getDataDeInternacao() {
		return dataDeInternacao;
	}

	public void setDataDeInternacao(String dataDeInternacao) {
		this.dataDeInternacao = dataDeInternacao;
	}

	public boolean isFumante() {
		return fumante;
	}

	public void setFumante(boolean fumante) {
		this.fumante = fumante;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public static ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public static void setPacientes(ArrayList<Paciente> pacientes) {
		Paciente.pacientes = pacientes;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getIdade() {
		return idade;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdQuarto() {
		return idQuarto;
	}

	public void setIdQuarto(long idQuarto) {
		this.idQuarto = idQuarto;
	}
	public Quarto getQuarto() {
		return quarto;
	}
}
