package br.feevale.droidhospital.db;

public class Quarto extends Interpretador {
	
	private static final long serialVersionUID = -8134978785938852291L;
	
	private Integer idLeito;
	private String quarto;
	private String leito;
	
	public Integer getIdLeito() {
		return idLeito;
	}
	
	public void setIdLeito( Integer idLeito ) {
		this.idLeito = idLeito;
	}
	
	public String getQuarto() {
		return quarto;
	}
	
	public void setQuarto( String quarto ) {
		this.quarto = quarto;
	}
	
	public String getLeito() {
		return leito;
	}
	
	public void setLeito( String leito ) {
		this.leito = leito;
	}
}