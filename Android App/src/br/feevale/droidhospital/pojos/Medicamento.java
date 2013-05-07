package br.feevale.droidhospital.pojos;

import java.util.ArrayList;

public class Medicamento {
	@SuppressWarnings("serial")
	public static ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>() {

		{
			add(new Medicamento(1, "Paracetamol", "Aché", "Tylenol", "250mg","cp"));
			add(new Medicamento(2, "Paracetamol", "Aché", "Tylenol Sinus","250mg", "cp"));
			add(new Medicamento(3, "Paracetamol", "Aché", "Tylenol", "500mg","cp"));
			add(new Medicamento(4, "Paracetamol", "Bayer", "Vick Vaporub","100ml", "pt"));
			add(new Medicamento(5, "Paracetamol", "Bayer", "Bayernol", "750mg","cp"));
			add(new Medicamento(6, "Paracetamol", "Bayer", "Vick Vaporub","300ml", "pt"));
			add(new Medicamento(7, "Paracetamol", "P&G", "Macarol", "250ml","se"));
			add(new Medicamento(8, "Paracetamol", "P&G", "Macarol", "250ml","am"));
			add(new Medicamento(9, "Paracetamol", "P&G", "Macarol", "250mg","cp"));
		}
	};

	public static Medicamento getMedicamentoById(long id) {
		for (int i = 0; i < medicamentos.size(); i++) {
			if (medicamentos.get(i).getId() == id) {
				return medicamentos.get(i);
			}
		}
		return null;
	}

	private long id;
	private String principio, laboratorio, fantasia, concentracao, forma;

	public static ArrayList<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public static void setMedicamentos(ArrayList<Medicamento> medicamentos) {
		Medicamento.medicamentos = medicamentos;
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

	public Medicamento(long id, String principio, String laboratorio,
			String fantasia, String concentracao, String forma) {
		this.id = id;
		this.principio = principio;
		this.laboratorio = laboratorio;
		this.fantasia = fantasia;
		this.concentracao = concentracao;
		this.forma = forma;

	}

}
