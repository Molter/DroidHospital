package droidhospital.service;

public class DroidHospitalException extends Exception {

	public static final int ERRO_DE_NEGOCIO = -1;
	
	private static final long serialVersionUID = 1L;
	private int nrErro = -1;

	public DroidHospitalException( Exception e ) {
		
		super( e.getMessage() );
		
		this.nrErro = -1;
		
		if( e instanceof DroidHospitalException ) {
			this.nrErro = ((DroidHospitalException) e).getNrErro();
		}
	}

	public DroidHospitalException( String msg ) {
		super( msg );
		this.nrErro = -1;
	}

	public DroidHospitalException( Exception e, int nrErro ) {
		super( e );
		this.nrErro = nrErro;
	}

	public DroidHospitalException( String msg, int nrErro ) {
		super( msg );
		this.nrErro = nrErro;
	}
	
	public int getNrErro() {
		return nrErro;
	}
}