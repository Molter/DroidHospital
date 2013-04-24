import java.io.IOException;

import droidhospital.service.DroidHospitalIPServer;

public class Chamador {

	public static void main( String[] args ) {
		
		try {

			System.out.println( "RODANDO O SERVIDOR!" );
			
			DroidHospitalIPServer ipServer = new DroidHospitalIPServer();
			
			ipServer.start();

		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}