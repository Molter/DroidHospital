package br.feevale.comunicacao;

import br.feevale.droidhospital.db.Interpretador;
import br.feevale.util.Serializador;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class EnviaTransacao {


    private String ipServidor = "192.168.0.112";
    private int nrPorta = 1444;
    private Interpretador interpretador;
    private Socket socket;

    public EnviaTransacao(Interpretador interpretador) throws UnknownHostException, IOException {

        this.interpretador = interpretador;

        socket = new Socket(ipServidor, nrPorta);
    }

    public void envia() throws IOException {

        socket.getOutputStream().write(Serializador.serialize(interpretador));
        socket.getOutputStream().flush();
    }

    public Serializable recebe() throws IOException, ClassNotFoundException {

        InputStream input = socket.getInputStream();

        ObjectInputStream ois = new ObjectInputStream(input);

        return (Serializable) ois.readObject();
    }

    public void fechaSocket() throws IOException {
        socket.close();
    }
}