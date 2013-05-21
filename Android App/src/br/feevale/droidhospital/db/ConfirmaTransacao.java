package br.feevale.droidhospital.db;

public class ConfirmaTransacao extends Interpretador {

	private static final long serialVersionUID = -1768147733502128870L;
	
	public static final int RESULT_FAIL = 0;
	public static final int RESULT_OK = 1;
	public static final int RESULT_DENIED = 2;
	
	private int result = RESULT_FAIL;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	

}
