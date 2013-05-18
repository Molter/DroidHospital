package br.feevale.droidhospital.db;

public class DadosUsuario extends Interpretador {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7993569717042590684L;
	private String tipoUsuario;
	private Integer idUsuario;
	
	public static String TIPO_MEDICO = "M";
	public static String TIPO_ENFERMEIRO = "E";
	public static String TIPO_PACIENTE = "P";
	public static String FAIL = "N";
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
