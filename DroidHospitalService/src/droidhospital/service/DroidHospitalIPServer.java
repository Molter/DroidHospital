package droidhospital.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DroidHospitalIPServer extends Thread {

	int nrPorta = 1444;
	private ServerSocket serverSocket;
	private boolean interrupcaoSaida = false;
	
	public DroidHospitalIPServer() throws IOException {
		serverSocket = new ServerSocket( nrPorta );
	}

	public void run() {
		
		while ( !interrupcaoSaida ) {
			
			try {
				
				Socket socket = serverSocket.accept();

				DroidHospitalService sismedService = new DroidHospitalService( socket );
				
				sismedService.start();
				
			} catch (IOException e) {
				System.out.println( "Erro ao rodar IPServer: " + e.getMessage() );
			}
		}
	}
	
	public void finaliza() {
		
		interrupcaoSaida = true;
		
		try {
			serverSocket.close();
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}