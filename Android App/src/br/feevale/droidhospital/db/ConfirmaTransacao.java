package br.feevale.droidhospital.db;

public class ConfirmaTransacao extends Interpretador {

	private static final long serialVersionUID = -1768147733502128870L;
	
	public static int RESULT_OK = 1;
	public static int RESULT_FAIL = 0;
	
	private int result;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	

}
