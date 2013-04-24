package br.feevale.droidhospital.pojos;

import java.util.ArrayList;

public class Quarto {
	public static ArrayList<Quarto> quartos = new ArrayList<Quarto>(){
		{
			add(new Quarto(1,"101","A"));
			add(new Quarto(2,"101","B"));
			add(new Quarto(3,"101","C"));
			add(new Quarto(4,"102","A"));
			add(new Quarto(5,"103","A"));
			add(new Quarto(6,"104","A"));
			add(new Quarto(7,"104","B"));
			add(new Quarto(8,"104","C"));
			add(new Quarto(9,"105","A"));
			add(new Quarto(10,"105","B"));
			add(new Quarto(11,"106","A"));
			add(new Quarto(12,"106","B"));
		}
	};
	
	public static Quarto getQuartoById(long id) {
		for (int i = 0; i < quartos.size(); i++) {
			if(quartos.get(i).getId() == id){
				return quartos.get(i);
			}
		}
		return null;
	}
	
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLeito() {
		return leito;
	}

	public void setLeito(String leito) {
		this.leito = leito;
	}

	private String nome, numero, leito;
	
	public Quarto(long id,String numero, String leito){
		this.id = id;
		this.numero = numero;
		this.leito = leito;
	}
	
}
