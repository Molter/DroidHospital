package br.feevale.droidhospital.pojos;

import java.util.ArrayList;

public class Medicamentos {
	
	private static ArrayList<Medicamentos> medicamentos;

	public static Medicamentos getMedicamentoById(long id) {
		for (int i = 0; i < medicamentos.size(); i++) {
			if (medicamentos.get(i).getId() == id) {
				return medicamentos.get(i);
			}
		}
		return null;
	}

	private long id;
	private String principio, laboratorio, fantasia, concentracao, forma;

	public static ArrayList<Medicamentos> getMedicamentos() {
		return medicamentos;
	}

	public static void setMedicamentos(ArrayList<Medicamentos> medicamentos) {
		Medicamentos.medicamentos = medicamentos;
	}

	public String getPrincipio() {
		return principio;
	}

	public void setPrincipio(String principio) {
		this.principio = principio;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getConcentracao() {
		return concentracao;
	}

	public void setConcentracao(String concentracao) {
		this.concentracao = concentracao;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Medicamentos(long id, String principio, String laboratorio,
			String fantasia, String concentracao, String forma) {
		this.id = id;
		this.principio = principio;
		this.laboratorio = laboratorio;
		this.fantasia = fantasia;
		this.concentracao = concentracao;
		this.forma = forma;

	}

}
