package droidhospital.service;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

public class DroidHospitalService extends Thread {

	private Socket socket;
	private OutputStream output;

	public DroidHospitalService( Socket socket ) {

		this.socket = socket;
	}

	public void run() {

		try {

			InputStream input = socket.getInputStream();
			
			output = socket.getOutputStream();
			
			ObjectInputStream ois = new ObjectInputStream( input );
			
			Serializable objeto = (Serializable) ois.readObject(); 
			
			Transacao transacao = Transacao.verificaTransacao( objeto );
			
			if( transacao != null ) {
				
				transacao.executaTransacao();
				enviaResposta( transacao.getDadosResposta() );
			}
			
			input.close();
			
			output.flush();
			output.close();
			socket.close();
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	private void enviaResposta( Serializable dadosResposta ) {
		
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream( output );
			
			oos.writeObject( dadosResposta );
			oos.flush();
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
}