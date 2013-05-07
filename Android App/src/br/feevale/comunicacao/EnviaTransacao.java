package br.feevale.comunicacao;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import br.feevale.droidhospital.db.Interpretador;
import br.feevale.util.Serializador;

public class EnviaTransacao {
	
	private String ipServidor = "192.168.2.44";
	private int nrPorta = 1444;
	private Interpretador interpretador;
	private Socket socket;
	
	public EnviaTransacao( Interpretador interpretador ) throws UnknownHostException, IOException {
		
		this.interpretador = interpretador;
		
		socket = new Socket( ipServidor, nrPorta );
	}
	
	public void envia() throws IOException {
		
		socket.getOutputStream().write( Serializador.serialize( interpretador ) );
		socket.getOutputStream().flush();
	}
	
	public Serializable recebe() throws IOException, ClassNotFoundException {
		
		InputStream input = socket.getInputStream();
		
		ObjectInputStream ois = new ObjectInputStream( input );
		
		return (Serializable) ois.readObject();
	}
	
	public void fechaSocket() throws IOException {
		socket.close();
	}
}